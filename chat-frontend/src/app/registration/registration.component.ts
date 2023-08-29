import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from '../models/user';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {

  registrationForm: FormGroup;

  constructor(private userService : UserService, private fb: FormBuilder, private router : Router) {
      this.registrationForm = this.fb.group({
        login: [null,[Validators.required, Validators.minLength(6)]],
        firstName: [null,[Validators.required]],
        password: [,[Validators.required, Validators.minLength(6)]],
        lastName: [null,[Validators.required]],
        email: [null,[Validators.required]]
      })
   }

  ngOnInit() {
  } 

  registerNewUser() : void {
    this.userService.registerUser(this.createFromForm()).subscribe(
      ()=> this.router.navigate(['/']),
      error => {
        const formControl = this.registrationForm.get('login');
        formControl.setErrors({internalServerError : error.error.message})
    });
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
