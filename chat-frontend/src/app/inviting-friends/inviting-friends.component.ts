import { Component, OnInit } from '@angular/core';
import { User } from '../models/user';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-inviting-friends',
  templateUrl: './inviting-friends.component.html',
  styleUrls: ['./inviting-friends.component.scss']
})
export class InvitingFriendsComponent implements OnInit {

  searchField : string;
  users: User[];

  constructor(private userService : UserService) {
      this.searchField = '';
      this.users = [];
   }

  ngOnInit() {

  }

  findUsersByLogin() : void {
    this.userService.searchForFriends(this.searchField).subscribe(response =>{
      this.users = response.body;
    })
  }

}
