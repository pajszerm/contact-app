import { Component, OnInit } from '@angular/core';
import {PageEvent} from "@angular/material/paginator";
import {UserDetailsModel} from "../../models/userDetails.model";
import {UserService} from "../../services/user.service";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  users!: UserDetailsModel[];
  pageSizeOptions: number[] = [3, 6, 9];
  pageIndex: number = 0;
  pageSize: number = 0;
  length: number = 0;
  defaultPageEvent: PageEvent = {pageSize: 6, pageIndex: 0, length: 0};

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.getServerData(this.defaultPageEvent);
  }

  handlePageEvent($event: PageEvent) {

  }

  private getServerData(defaultPageEvent: PageEvent) {

  }
}
