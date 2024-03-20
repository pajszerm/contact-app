import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {UserFormModel} from "../models/userForm.model";
import {Observable} from "rxjs";
import {PageEvent} from "@angular/material/paginator";
import {UserDetailsResponseModel} from "../models/userDetailsResponse.model";

const BASE_URL = 'http://localhost:8080/api/users'

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  saveUserForm(data: UserFormModel): Observable<any> {
    return this.http.post(`${BASE_URL}/new-contact`, data);
  }

  getUserDetails(event: PageEvent): Observable<UserDetailsResponseModel> {
    const params = new HttpParams()
      .set('page', event.pageIndex.toString())
      .set('size', event.pageSize.toString());
    return this.http.get<UserDetailsResponseModel>(`${BASE_URL}/all`, { params });
  }

  deleteUserDetails(username: string | undefined): Observable<void> {
    return this.http.delete<void>(`${BASE_URL}/delete/${username}`);
  }
}
