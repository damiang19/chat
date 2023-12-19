import { AfterViewChecked, Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { CompatClient, Stomp } from '@stomp/stompjs';
import { WebSocketService } from '../services/WebSocket.service';
import * as SockJS from 'sockjs-client';
import { UserService } from '../services/user.service';
import { User } from '../models/user';
import { Conversation } from '../models/conversation';
import { MessageRequest } from '../models/message-request';
import { FriendsIntegrationService } from '../services/friends-integration.service';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'chat-view',
  templateUrl: './chat-view.component.html',
  styleUrls: ['./chat-view.component.scss']
})
export class ChatViewComponent implements OnInit, AfterViewChecked  {
  @ViewChild('scrollMe') private myScrollContainer: ElementRef;

  name : string;
  file : File;
  message : string;
  currentUser: string;
  stompClient : CompatClient;
  conversation: Conversation;
  userList : User[];
  messageRequest: MessageRequest;
  imagePath: any;
  
  constructor( private ws : WebSocketService,private userService : UserService, private friendsIntegrationService : FriendsIntegrationService, private sanitizer : DomSanitizer) { 
    this.message = '';
    this.userList = [];
    this.currentUser = '';
    this.stompClient = null;
    this.conversation = new Conversation();
    this.conversation.messages = [];
    this.messageRequest = new MessageRequest()
  }

  ngOnInit() {
    this.fetchUserData();
    this.scrollToBottom();
    this.fetchFriendList();
  }

  ngAfterViewChecked() {        
    this.scrollToBottom();     
  } 

  scrollToBottom(): void {
    try {
        this.myScrollContainer.nativeElement.scrollTop = this.myScrollContainer.nativeElement.scrollHeight;
    } catch(err) { }                 
  }

  colourConversation(index : number): string {
    return index %2 == 0 ? 'yellow' : 'grey'
  }

  openConversation(friendLogin : string): void {
    this.friendsIntegrationService.getConversation(friendLogin).subscribe(response =>{
      this.conversation = response.body;
    }, err => {}, 
    () =>  {
      this.imagePath = this.sanitizer.bypassSecurityTrustResourceUrl('data:image/png;base64,' 
    + this.conversation.messages[15].encodedFile)
    console.log(this.imagePath)
    });
  }

connect() {
  const socket = new SockJS('http://localhost:8080/websocket/yol');
  this.stompClient = Stomp.over(socket);
  const _this = this;
  this.stompClient.connect({ username : this.currentUser}, function (frame) {
    _this.stompClient.subscribe('/users/topic/messages', function (hello) {
    _this.showConversation(JSON.parse(hello.body));
    });
  });
}

showConversation(message : any) {
  this.sanitizer.bypassSecurityTrustResourceUrl('data:image/jpg;base64,', )
  if(message.conversationId === this.conversation.id){
    this.conversation.messages.push(message);
  } 
}

disconnect() {
  if (this.stompClient != null) {
    this.stompClient.disconnect();
  } 
}


sendMessage(content : any){
  this.prepareMessageToSend(content.target.value);
  this.stompClient.send("/message-broker", {'Authorization':'Bearer'}, JSON.stringify(this.messageRequest));
  content.target.value = '';
}

prepareMessageToSend(content : string){ 
  this.messageRequest.content = content;
  this.messageRequest.conversationId = this.conversation.id;
  this.messageRequest.author = this.currentUser;
}


fetchFriendList() : void {
 this.friendsIntegrationService.getFriends().subscribe(response => {
  this.userList = response.body;
  });
 }

fetchUserData() : void { 
  this.userService.getCurrentUser().subscribe(response => {
    this.currentUser = response.body.login;
  },() => {}, () => this.connect());
 }

 sendFile(file ?: File) : void {
    console.log(file);
    
    this.friendsIntegrationService.sendFile(file, this.conversation.id).subscribe();
 }

}
