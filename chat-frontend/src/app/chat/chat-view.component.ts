import { Component, OnInit } from '@angular/core';
import { CompatClient, Stomp } from '@stomp/stompjs';
import { WebSocketService } from '../services/WebSocket.service';
import * as SockJS from 'sockjs-client';
import jwt_decode from 'jwt-decode'
import { JwtService } from '../services/jwt.service';
import { UserService } from '../services/user.service';
import { User } from '../models/user';
import { Conversation } from '../models/conversation';

@Component({
  selector: 'chat-view',
  templateUrl: './chat-view.component.html',
  styleUrls: ['./chat-view.component.scss']
})
export class ChatViewComponent implements OnInit {
  name : string;
  message : string;
  stompClient : CompatClient;
  conversation: Conversation;
  userList : User[];
  
  constructor( private ws : WebSocketService,private jwtService: JwtService,private userService : UserService) { 
    this.name = '';
    this.message = '';
    this.userList = [];
    this.stompClient = null;
    this.conversation = new Conversation();
    this.conversation.messages = [];
    const key = jwt_decode(localStorage.getItem(JwtService.TOKEN_STORAGE_KEY));
  }

  ngOnInit() {
    this.connect();
    this.userService.getFriends().subscribe(response => {
      console.log(response.body);
      this.userList = response.body;
    })
  }

  openConversation(conversation: Conversation): void {
    this.userService.getConversation().subscribe(response =>{
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
    _this.showGreeting(hello.body);
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
showGreeting(message : any) {
this.message = message;
}

sendMessage(){
  this.stompClient.send("/hello", {},);
}

}
