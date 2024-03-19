import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {UserFormModel} from "../models/userForm.model";
import {Observable} from "rxjs";

const BASE_URL = 'http://localhost:8080/api/users'

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  saveUserForm(data: UserFormModel): Observable<any> {
    return this.http.post(BASE_URL + '/new-contact', data)
  }
}
