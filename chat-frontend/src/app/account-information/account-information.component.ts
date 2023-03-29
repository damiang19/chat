import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from '../models/user';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-account-information',
  templateUrl: './account-information.component.html',
  styleUrls: ['./account-information.component.scss']
})
export class AccountInformationComponent implements OnInit {

  registrationForm: FormGroup;
  currentUser: User;

  constructor(private userService : UserService, private fb: FormBuilder, private router : Router) {
      this.registrationForm = this.fb.group({
        login: [null,[Validators.required]],
        firstName: [null,[Validators.required]],
        password: [null,[Validators.required, Validators.minLength(6)]],
        lastName: [null,[Validators.required]],
      })
   }
  ngOnInit() {
    this.userService.getCurrentUser().subscribe(response =>{
      this.currentUser = response.body;
    }, error =>{}, ()=> this.patchForm());
  } 

  updateAccountInformation() : void {
      this.userService.updateUser(this.createFromForm()).subscribe()
  }

  deleteAccount(): void {
      this.userService.deleteAccount(this.currentUser.id).subscribe();
  }

  private patchForm(): void {
  this.registrationForm.patchValue({
    login: this.currentUser.login,
    firstName: this.currentUser.firstName,
    lastName: this.currentUser.lastName,
    password: this.currentUser.password,
  })}

  private createFromForm(): User {
    return {
      ...new User(),
      login: this.registrationForm.get(['login'])!.value,
      firstName: this.registrationForm.get(['firstName'])!.value,
      lastName: this.registrationForm.get(['lastName'])!.value,
      password: this.currentUser.password,
    }

  }

}
