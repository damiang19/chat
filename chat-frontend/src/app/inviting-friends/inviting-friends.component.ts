import { Component, OnInit } from '@angular/core';
import { User } from '../models/user';
import { FriendsIntegrationService } from '../services/friends-integration.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-inviting-friends',
  templateUrl: './inviting-friends.component.html',
  styleUrls: ['./inviting-friends.component.scss']
})
export class InvitingFriendsComponent implements OnInit {

  users: User[];
  currentUser: User;

  constructor(private friendsIntegrationService : FriendsIntegrationService, private userService: UserService) {
      this.users = [];
   }

  ngOnInit() {
    this.userService.getCurrentUser().subscribe(response => {
      this.currentUser = response.body;
    })
  }

  sendFriendInvitation(login : string): void {
    this.friendsIntegrationService.sendFriendInvitation(login).subscribe();
  }

  findUsersByLogin(login : string) : void {
    this.friendsIntegrationService.searchForFriends(login).subscribe(response =>{
      this.users = response.body;
    })
  }

  showSendFriendInvitationButton(login : string) : boolean {
    if(!this.currentUser.friends){
      return true;
    }
    const existOnFriendsList = this.currentUser.friends.findIndex(element => element == login);
    if(existOnFriendsList === -1){
      return true;
    }else {
      return false
    }
  }

}
