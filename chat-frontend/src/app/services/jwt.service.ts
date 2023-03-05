import { HttpClient, HttpHeaders, HttpResponse } from "@angular/common/http";
import { Router } from "@angular/router";
import { environment } from 'src/environments/environment';
import { Credentials } from "../models/credentials";
import { Observable } from 'rxjs';
import { Injectable } from "@angular/core";

const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
    observe: 'response' as 'response'
  };
  const API_URL = 'http://localhost:8080';

  @Injectable({
    providedIn: 'root'
  })
export class JwtService { 
    static readonly TOKEN_STORAGE_KEY = 'token';
    redirectToUrl: string = '/chat';
  
    constructor(private httpClient: HttpClient,private router: Router) { }
  
  
    public login(credentials: Credentials): void {
      this.getResponseHeaders(credentials)
      .subscribe((res: HttpResponse<any>) => {
        this.saveToken(res.body.token);
        this.router.navigate([this.redirectToUrl]);
      });
    }
   
     public getResponseHeaders(credentials: Credentials) {
      let loginUrl = API_URL + '/authenticate';
      return this.httpClient.post(loginUrl, credentials, httpOptions);
    }
    private saveToken(token: string){
      localStorage.setItem(JwtService.TOKEN_STORAGE_KEY, token);
    }
  
    public getToken(): string {
      return localStorage.getItem(JwtService.TOKEN_STORAGE_KEY);
    }

    public removeToken(): void {
      localStorage.removeItem(JwtService.TOKEN_STORAGE_KEY);
    }

    public isLoggedIn(): boolean {
      
      if(localStorage.getItem(JwtService.TOKEN_STORAGE_KEY) !== null){
        return true; 
      } else {
        return false;
      }
    }
}