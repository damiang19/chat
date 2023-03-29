import { Component, OnInit } from '@angular/core';
import { User } from '../models/user';
import { FriendsIntegrationService } from '../services/friends-integration.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-friend-invitations',
  templateUrl: './friend-invitations.component.html',
  styleUrls: ['./friend-invitations.component.scss']
})
export class FriendInvitationsComponent implements OnInit {

   invitingFriends : User[];

  constructor(protected userService : UserService, protected firendIntegrationService : FriendsIntegrationService) { }

  ngOnInit() {
    this.firendIntegrationService.getFriendsInvitations().subscribe(response => {
      this.invitingFriends = response.body;
    })
  }

  acceptInvitationToFriend(login : string): void {
    this.firendIntegrationService.acceptFriendInvitations(login).subscribe();
  }


}
