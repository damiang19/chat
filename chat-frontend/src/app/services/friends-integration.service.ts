import { HttpClient, HttpHeaders, HttpResponse, HttpResponseBase } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { Observable } from "rxjs";
import { Conversation } from "../models/conversation";
import { User } from "../models/user";

const API_URL = 'http://localhost:8080';

@Injectable({providedIn: 'root'})
export class FriendsIntegrationService {

    constructor(private httpClient: HttpClient,private router: Router) { }

    public searchForFriends(userLogin: string): Observable<HttpResponse<User[]>> {
        return this.httpClient.get<User[]>(API_URL + '/search-for-friends', {observe: 'response', params: {login: userLogin}});
    }

    public getFriends(): Observable<HttpResponse<User[]>> {
        return this.httpClient.get<User[]>(API_URL+'/friends',{observe:'response'})
    }

    public getFriendsInvitations(): Observable<HttpResponse<User[]>> {
        return this.httpClient.get<User[]>(API_URL+'/friends-invitations',{observe:'response'})
    }
      
    public getConversation(friendLogin: string): Observable<HttpResponse<Conversation>> {
        return this.httpClient.get<Conversation>(API_URL+'/friend/conversation',{observe:'response', params:{'friendLogin': friendLogin}})
    }

    public sendFriendInvitation(login: string): Observable<HttpResponseBase> {
        return this.httpClient.put(API_URL+'/send-friend-invitation', {}, {observe:'response',params:{'login': login}})
    }

    public acceptFriendInvitations(login: string): Observable<HttpResponseBase> {
        return this.httpClient.put(API_URL+'/accept-friend-invitation', {}, {observe:'response',params:{'login': login}})
    }

    public sendFile(file : File, conversationIdentity : number) : Observable<HttpResponse<any>> {
        const formdata: FormData = new FormData();  
        var blob = new Blob([file], { type: "text/xml"});
        formdata.set('file', blob);  
        const headers = new HttpHeaders()
        headers.set('Content-Type','multipart/form-data')
        return this.httpClient.post<any>(API_URL+'/photos/add', formdata , {params:{'conversationIdentity': String(conversationIdentity)} })
    }


}