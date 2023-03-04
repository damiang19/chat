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

    public registerUser(user: User): Observable<HttpResponse<User>> {
        return this.httpClient.post<User>(API_URL + '/register', user, {observe: 'response'});
    }

    public getCurrentUser(): Observable<HttpResponse<User>> {
        return this.httpClient.get<User>(API_URL+'/current-user',{observe:'response'})
    }
}
