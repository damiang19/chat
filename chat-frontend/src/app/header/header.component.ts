import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { JwtService } from '../services/jwt.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  isLoggedIn : boolean;
  jwtToken : string;

  constructor(private jwtService : JwtService, private router : Router) {
     this.jwtToken = jwtService.getToken();
  }

  ngOnInit() {
    this.router.events.subscribe(event => {
      this.isLoggedIn = this.jwtService.isLoggedIn();
    });
  }

  logout() : void {
    this.jwtService.removeToken();
    this.router.navigate(['/']);
  }

}
