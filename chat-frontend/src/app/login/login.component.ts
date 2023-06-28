import { Component, OnInit } from '@angular/core';
import { JwtService } from 'src/app/services/jwt.service';
import { Credentials } from '../models/credentials';
import jwt_decode from 'jwt-decode'

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  credentials : Credentials;

  constructor(private jwtClientService: JwtService) { 
    this.credentials = new Credentials();
  }

  ngOnInit() {
  }
  

  login():void {
    this.jwtClientService.login(this.credentials);
  }
}
