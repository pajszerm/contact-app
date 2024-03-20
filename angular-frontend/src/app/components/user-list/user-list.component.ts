import {Component, OnInit} from '@angular/core';
import {PageEvent} from "@angular/material/paginator";
import {UserDetailsModel} from "../../models/userDetails.model";
import {UserService} from "../../services/user.service";
import {Router} from "@angular/router";

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
  defaultPageEvent: PageEvent = {pageSize: 3, pageIndex: 0, length: 0};
  userDetailsModel?: UserDetailsModel;

  constructor(private userService: UserService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.handlePageEvent(this.defaultPageEvent);
  }

  handlePageEvent(event: PageEvent) {
    this.userService.getUserDetails(event).subscribe({
      next: (data) => {
        this.users = data.users;
        this.length = data.length;
        this.pageIndex = data.pageIndex;
        this.pageSize = data.pageSize;
      },
      error: err => console.warn(err),
    })
    return event;
  }

  openUserDetailsModal(user: UserDetailsModel) {
    const modelDiv = document.getElementById('detailsModal');
    this.userDetailsModel = user;
    if (modelDiv != null) {
      modelDiv.style.display = 'block';
    }
  }

  closeEditModal() {
    const modelDiv = document.getElementById('detailsModal');
    if (modelDiv != null) {
      modelDiv.style.display = 'none';
    }
  }

  deleteUserDetails(username: string | undefined) {
    this.userService.deleteUserDetails(username).subscribe({
      next: () => {},
      error: (err) => console.warn(err),
      complete: () => {
        this.closeEditModal()
        const event: PageEvent = {
          pageSize: this.pageSize,
          pageIndex: this.pageIndex,
          length: this.length
        };
        this.handlePageEvent(event);
      }
    })
  }
}
