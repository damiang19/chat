import { Injectable } from "@angular/core";
import { Stomp } from "@stomp/stompjs";

import * as SockJS from 'sockjs-client';
@Injectable({
    providedIn: 'root'
  })

export class WebSocketService {
  private stompClient = null;
  
      connect() {
    const socket = new SockJS('http://localhost:8080/websocket/yol');
    this.stompClient = Stomp.over(socket);
    const _this = this;
    this.stompClient.connect({username: 'admin'}, function (frame) {
      debugger;
      _this.setConnected(true);
      _this.stompClient.subscribe('users/topic/messages', function (hello) {
        _this.showGreeting(JSON.parse(hello.body));
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
  }

  sendMessage(message : string){
      this.stompClient.send("/hello", {}, 
                 message);
  }

}