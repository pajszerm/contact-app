import {Component, OnInit} from '@angular/core';
import {FormArray, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {UserService} from "../../services/user.service";
import {UserFormModel} from "../../models/userForm.model";
import {handleValidationErrors} from "../../utils/validation-handler";


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
      username: ['', Validators.required],
      name: ['', Validators.required],
      mothersName: ['', Validators.required],
      email: [''],
      birthDate: [null, Validators.required],
      tajNumber: ['', Validators.required],
      taxNumber: ['', Validators.required],
      phoneNumbers: this.formBuilder.array([]),
      addresses: this.formBuilder.array([])
    })
  }

  ngOnInit(): void {}

  saveUserForm() {
    const formData: UserFormModel = this.userForm.value;
    this.userService.saveUserForm(formData).subscribe({
      next: value => {
      },
      error: err => handleValidationErrors(err, this.userForm),
      complete: () => {
        this.userForm.reset();
        this.router.navigate(['user-list']);
      }
    });
  }

  addPhoneNumber() {
    (this.userForm.get('phoneNumbers') as FormArray).push(this.formBuilder.group({
      phoneNumber: ['', Validators.required]
    }));
  }

  addAddress() {
    (this.userForm.get('addresses') as FormArray).push(this.formBuilder.group({
      cityName: ['', Validators.required],
      streetName: ['', Validators.required],
      houseNumber: ['', Validators.required],
      postalCode: ['', Validators.required]
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

  hasAddress(): boolean {
    const addresses = this.userForm.get('addresses') as FormArray;
    return addresses.length > 0;
  }
}
