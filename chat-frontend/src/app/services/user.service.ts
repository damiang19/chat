import { HttpClient, HttpParams, HttpResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { Observable } from "rxjs";
import { Conversation } from "../models/conversation";
import { User } from "../models/user";

const API_URL = 'http://localhost:8080';

@Injectable({providedIn: 'root'})
export class UserService {

    constructor(private httpClient: HttpClient,private router: Router) { }

    public getFriends(): Observable<HttpResponse<User[]>> {
        return this.httpClient.get<User[]>(API_URL+'/friends',{observe:'response'})
    }
      
    public getConversation(friendLogin: string): Observable<HttpResponse<Conversation>> {
        return this.httpClient.get<Conversation>(API_URL+'/friend/conversation',{observe:'response', params:{'friendLogin': friendLogin}})
    }

    public registerUser(user: User): Observable<HttpResponse<User>> {
        return this.httpClient.post<User>(API_URL + '/register', user, {observe: 'response'});
    }

    public searchForFriends(userLogin: string): Observable<HttpResponse<User[]>> {
        return this.httpClient.get<User[]>(API_URL + '/search-for-friends', {observe: 'response', params: {login: userLogin}});
    }

}
