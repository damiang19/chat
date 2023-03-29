import {  NgModule } from '@angular/core';
import { Routes, RouterModule, CanActivate, Router } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { ChatViewComponent,  } from './chat/chat-view.component';
import { RegistrationComponent } from './registration/registration.component';
import { InvitingFriendsComponent } from './inviting-friends/inviting-friends.component';
import { OnlyLoggedInUsersGuard } from './services/logged-user.service';
import { FriendInvitationsComponent } from './friend-invitations/friend-invitations.component';
import { AccountInformationComponent } from './account-information/account-information.component';


const routes: Routes = [
  {path : '', component : LoginComponent},
  {path : 'chat', component : ChatViewComponent,canActivate : [OnlyLoggedInUsersGuard]},
  {path: 'register', component: RegistrationComponent},
  {path: 'friend-invitations', component: FriendInvitationsComponent, canActivate: [OnlyLoggedInUsersGuard]},
  {path: 'account-information', component: AccountInformationComponent, canActivate: [OnlyLoggedInUsersGuard]},
  {path: 'search-for-friends', component: InvitingFriendsComponent, canActivate: [OnlyLoggedInUsersGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes),],
  exports: [RouterModule],
  providers:[OnlyLoggedInUsersGuard]
})
export class AppRoutingModule { }
