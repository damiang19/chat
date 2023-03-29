import { HttpClient, HttpParams, HttpResponse, HttpResponseBase } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { User } from "../models/user";

const API_URL = 'http://localhost:8080';

@Injectable({providedIn: 'root'})
export class UserService {

    constructor(private httpClient: HttpClient) { }

    public registerUser(user: User): Observable<HttpResponse<User>> {
        return this.httpClient.post<User>(API_URL + '/register', user, {observe: 'response'});
    }

    
    public getCurrentUser(): Observable<HttpResponse<User>> {
        return this.httpClient.get<User>(API_URL+'/current-user',{observe:'response'})
    }

    public updateUser(user: User): Observable<HttpResponse<User>> {
        return this.httpClient.put<User>(API_URL+'/update-user', user, {observe:'response'})
    }

    public deleteAccount(id: number): Observable<HttpResponse<HttpResponseBase>> {
        return this.httpClient.delete<HttpResponseBase>(API_URL +'/update-user/' + id, {observe:'response'})
    }
}
