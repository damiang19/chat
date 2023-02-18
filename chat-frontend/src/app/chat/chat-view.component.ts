import { Component, OnInit } from '@angular/core';
import { CompatClient, Stomp } from '@stomp/stompjs';
import { WebSocketService } from '../services/WebSocket.service';
import * as SockJS from 'sockjs-client';
import jwt_decode, { JwtDecodeOptions } from 'jwt-decode'
import { JwtService } from '../services/jwt.service';
import { UserService } from '../services/user.service';
import { User } from '../models/user';
import { Conversation } from '../models/conversation';
import { MessageRequest } from '../models/message-request';
import { Message } from '@angular/compiler/src/i18n/i18n_ast';
import { Messages } from '../models/messages';

@Component({
  selector: 'chat-view',
  templateUrl: './chat-view.component.html',
  styleUrls: ['./chat-view.component.scss']
})
export class ChatViewComponent implements OnInit {
  name : string;
  message : string;
  currentUser: string;
  stompClient : CompatClient;
  conversation: Conversation;
  userList : User[];
  messageRequest: MessageRequest;
  
  constructor( private ws : WebSocketService,private jwtService: JwtService,private userService : UserService) { 
    this.name = '';
    this.message = '';
    this.userList = [];
    this.currentUser = '';
    this.stompClient = null;
    this.conversation = new Conversation();
    this.conversation.messages = [];
    this.messageRequest = new MessageRequest()
    const key = jwt_decode(localStorage.getItem(JwtService.TOKEN_STORAGE_KEY));
    this.name = key['sub'];
  }

  ngOnInit() {
    this.connect();
    this.userService.getFriends().subscribe(response => {
      console.log(response.body);
      this.userList = response.body;
    })
  }

  colourConversation(index : number): string {
    return index %2 == 0 ? 'white' : 'grey'
  }

  openConversation(friendLogin : string): void {
    this.currentUser = friendLogin;
    this.userService.getConversation(friendLogin).subscribe(response =>{
      this.conversation = response.body;
    })
  }

connect() {
const socket = new SockJS('http://localhost:8080/websocket/yol');
this.stompClient = Stomp.over(socket);
const _this = this;
this.stompClient.connect({username: this.name}, function (frame) {
  _this.setConnected(true);
  _this.stompClient.subscribe('/users/topic/messages', function (hello) {
    _this.showConversation(hello.body);
  });
});
}

disconnect() {
  if (this.stompClient != null) {
    this.stompClient.disconnect();
  } 
  this.setConnected(false);
  console.log('Disconnected!');
}

setConnected(connected: boolean) {
if (connected) {
  console.log('witaj');
}

}
showConversation(message : any) {
  const n = new Messages();
  n.content = message;
  this.conversation.messages.push(n);
  console.log(this.conversation.messages);
}

sendMessage(content : string){
  this.prepareMessageToSend(content);
  this.stompClient.send("/hello", {}, JSON.stringify(this.messageRequest));
}

prepareMessageToSend(content : string){
  this.messageRequest.content = content;
  this.messageRequest.conversationId = this.conversation.id;
  this.messageRequest.receiver = this.currentUser;
}

}
