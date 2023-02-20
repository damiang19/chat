import { Component, OnInit } from '@angular/core';
import { User } from '../models/user';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-inviting-friends',
  templateUrl: './inviting-friends.component.html',
  styleUrls: ['./inviting-friends.component.scss']
})
export class InvitingFriendsComponent implements OnInit {

  users: User[];

  constructor(private userService : UserService) {
      this.users = [];
   }

  ngOnInit() {

  }

  findUsersByLogin(login : string) : void {
    console.log(login)
    this.userService.searchForFriends(login).subscribe(response =>{
      this.users = response.body;
    })
  }

}
