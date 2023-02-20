import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from '../models/user';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {

  registrationForm: FormGroup;
  user: User;

  constructor(private userService : UserService, private fb: FormBuilder) {
      this.user = new User();
      this.registrationForm = this.fb.group({
        login: [null,[Validators.required, Validators.minLength(6)]],
        firstName: [null,[Validators.required]],
        password: [null,[Validators.required, Validators.minLength(6)]],
        lastName: [null,[Validators.required]],
        email: [null,[Validators.required]]
      })
   }

  ngOnInit() {
  } 

  registerNewUser() : void {
    this.userService.registerUser(this.createFromForm()).subscribe();
  }

  private createFromForm(): User {
    return {
      ...new User(),
      login: this.registrationForm.get(['login'])!.value,
      firstName: this.registrationForm.get(['firstName'])!.value,
      lastName: this.registrationForm.get(['lastName'])!.value,
      password: this.registrationForm.get(['password'])!.value,
      email: this.registrationForm.get(['email'])!.value,
    }
  }
}
