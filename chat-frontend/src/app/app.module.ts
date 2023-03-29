import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { JwtInterceptor } from './services/interceptor.service';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HeaderComponent } from './header/header.component';
import { ChatViewComponent } from './chat/chat-view.component';
import { RegistrationComponent } from './registration/registration.component';
import { InvitingFriendsComponent } from './inviting-friends/inviting-friends.component';
import { FriendInvitationsComponent } from './friend-invitations/friend-invitations.component';
import { AccountInformationComponent } from './account-information/account-information.component';

@NgModule({
  declarations: [				
    AppComponent,
    LoginComponent,
    ChatViewComponent,
    HeaderComponent,
    RegistrationComponent,
    InvitingFriendsComponent,
      FriendInvitationsComponent,
      AccountInformationComponent
   ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [{provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
