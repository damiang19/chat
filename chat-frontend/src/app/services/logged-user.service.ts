import { Injectable } from "@angular/core";
import { CanActivate, Router } from "@angular/router";
import { JwtService } from "./jwt.service";

@Injectable({providedIn: 'root'})
export class OnlyLoggedInUsersGuard implements CanActivate { 
  constructor(private jwtService: JwtService, private router : Router) {}; 

  canActivate() {
    if (this.jwtService.isLoggedIn()) { 
      return true;
    } else {
      this.router.navigate(['/']);
      return false;
    }
  }

}