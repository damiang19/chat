import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { ChatViewComponent,  } from './chat/chat-view.component';
import { RegistrationComponent } from './registration/registration.component';

const routes: Routes = [
  {path : '', component : LoginComponent},
  {path : 'chat', component : ChatViewComponent},
  {path: 'register', component: RegistrationComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
