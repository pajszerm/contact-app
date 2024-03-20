import {Component, OnInit} from '@angular/core';
import {FormArray, FormBuilder, FormGroup} from "@angular/forms";
import {Router} from "@angular/router";
import {UserService} from "../../services/user.service";
import {UserFormModel} from "../../models/userForm.model";

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css']
})
export class UserFormComponent implements OnInit {

  userForm: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private userService: UserService) {
    this.userForm = formBuilder.group({
      username: [''],
      name: [''],
      mothersName: [''],
      email: [''],
      birthDate: [null],
      tajNumber: [''],
      taxNumber: [''],
      phoneNumbers: this.formBuilder.array([]),
      addresses: this.formBuilder.array([])
    })
  }

  ngOnInit(): void {}

  saveUserForm() {
    const formData: UserFormModel = this.userForm.value;

    console.log(formData);

    this.userService.saveUserForm(formData).subscribe({
      next: value => {
      },
      error: err => {
        console.warn(err);
      },
      complete: () => {
        this.userForm.reset();
        this.router.navigate(['user-list']);
      }
    });
  }

  addPhoneNumber() {
    (this.userForm.get('phoneNumbers') as FormArray).push(this.formBuilder.group({
      phoneNumber: ['']
    }));
  }

  addAddress() {
    (this.userForm.get('addresses') as FormArray).push(this.formBuilder.group({
      cityName: [''],
      streetName: [''],
      houseNumber: [''],
      postalCode: ['']
    }));
  }

  removePhoneNumber(index: number) {
    (this.userForm.get('phoneNumbers') as FormArray).removeAt(index);
  }

  removeAddress(index: number) {
    (this.userForm.get('addresses') as FormArray).removeAt(index);
  }

  get phoneNumbers() {
    return (this.userForm.get('phoneNumbers') as FormArray).controls;
  }

  get addresses() {
    return (this.userForm.get('addresses') as FormArray).controls;
  }
}
