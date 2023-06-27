import { AfterViewChecked, Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { CompatClient, Stomp } from '@stomp/stompjs';
import { WebSocketService } from '../services/WebSocket.service';
import * as SockJS from 'sockjs-client';
import { UserService } from '../services/user.service';
import { User } from '../models/user';
import { Conversation } from '../models/conversation';
import { MessageRequest } from '../models/message-request';
import { FriendsIntegrationService } from '../services/friends-integration.service';

@Component({
  selector: 'chat-view',
  templateUrl: './chat-view.component.html',
  styleUrls: ['./chat-view.component.scss']
})
export class ChatViewComponent implements OnInit, AfterViewChecked  {
  @ViewChild('scrollMe') private myScrollContainer: ElementRef;

  name : string;
  message : string;
  currentUser: string;
  stompClient : CompatClient;
  conversation: Conversation;
  userList : User[];
  messageRequest: MessageRequest;
  
  constructor( private ws : WebSocketService,private userService : UserService, private friendsIntegrationService : FriendsIntegrationService) { 
    this.message = '';
    this.userList = [];
    this.currentUser = '';
    this.stompClient = null;
    this.conversation = new Conversation();
    this.conversation.messages = [];
    this.messageRequest = new MessageRequest()
  }

  ngOnInit() {
    this.userService.getCurrentUser().subscribe(response => {
        this.currentUser = response.body.login;
    },() => {}, () => this.connect())
    this.scrollToBottom();
    this.friendsIntegrationService.getFriends().subscribe(response => {
      this.userList = response.body;
    })
  }

  colourConversation(index : number): string {
    return index %2 == 0 ? 'yellow' : 'grey'
  }

  openConversation(friendLogin : string): void {
    this.friendsIntegrationService.getConversation(friendLogin).subscribe(response =>{
      this.conversation = response.body;
    })
  }

connect() {
const socket = new SockJS('http://localhost:8080/websocket/yol');
this.stompClient = Stomp.over(socket);
const _this = this;
this.stompClient.connect({ username : this.currentUser}, function (frame) {
  _this.setConnected(true);
  _this.stompClient.subscribe('/users/topic/messages', function (hello) {
    _this.showConversation(JSON.parse(hello.body));
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
  if(message.conversationId === this.conversation.id){
    this.conversation.messages.push(message);
  } else {
    
  }
}

sendMessage(content : any){
  this.prepareMessageToSend(content.target.value);
  this.stompClient.send("/hello", {'Authorization':'Bearer'}, JSON.stringify(this.messageRequest));
  content.target.value = '';
}

prepareMessageToSend(content : string){
  this.messageRequest.content = content;
  this.messageRequest.conversationId = this.conversation.id;
  this.messageRequest.author = this.currentUser;

}

ngAfterViewChecked() {        
  this.scrollToBottom();     
} 

scrollToBottom(): void {
  try {
      this.myScrollContainer.nativeElement.scrollTop = this.myScrollContainer.nativeElement.scrollHeight;
  } catch(err) { }                 
}

}
