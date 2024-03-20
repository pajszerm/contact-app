import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormArray, FormBuilder, FormGroup, ValidatorFn, Validators} from "@angular/forms";
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
      username: ['', [Validators.required, this.lettersAndSpacesValidator()]],
      name: ['', [Validators.required, this.lettersAndSpacesValidator()]],
      mothersName: ['', [Validators.required, this.lettersAndSpacesValidator()]],
      email: ['', Validators.email],
      birthDate: [null, Validators.required,],
      tajNumber: ['', [Validators.required, this.tajNumberValidator()]],
      taxNumber: ['', [Validators.required, this.taxNumberValidator()]],
      phoneNumbers: this.formBuilder.array([]),
      addresses: this.formBuilder.array([])
    })
  }

  ngOnInit(): void {
  }

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
      phoneNumber: ['', [Validators.required, this.phoneNumberValidator()]]
    }));
  }

  addAddress() {
    (this.userForm.get('addresses') as FormArray).push(this.formBuilder.group({
      cityName: ['', [Validators.required, this.lettersAndSpacesValidator()]],
      streetName: ['', [Validators.required, this.lettersAndSpacesValidator()]],
      houseNumber: ['', [Validators.required, this.onlyNumbersValidator()]],
      postalCode: ['', [Validators.required, this.postalCodeValidator()]]
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

  tajNumberValidator(): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any } | null => {
      const value = control.value;
      const isNumeric = /^\d+$/.test(value);
      const isValidLength = value && value.length === 9;
      return isNumeric && isValidLength ? null : {'invalidTajNumber': true};
    };
  }

  taxNumberValidator(): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any } | null => {
      const value = control.value;
      const isNumeric = /^\d+$/.test(value);
      const isValidLength = value && value.length === 8;
      return isNumeric && isValidLength ? null : {'invalidTaxNumber': true};
    };
  }

  postalCodeValidator(): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any } | null => {
      const postalCode = control.value;
      const isValid = /^\d{4}$/.test(postalCode);
      return isValid ? null : {'invalidPostalCode': {value: postalCode}};
    };
  }

  onlyNumbersValidator(): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any } | null => {
      const houseNumber = control.value;
      const isValid = /^\d+$/.test(houseNumber);
      return isValid ? null : {'invalidHouseNumber': {value: houseNumber}};
    };
  }

  phoneNumberValidator(maxLength: number = 15): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any } | null => {
      const value = control.value;
      const isValid = /^\d{1,15}$/.test(value);

      return isValid ? null : {'invalidNumber': {value}};
    };
  }

  lettersAndSpacesValidator(): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any } | null => {
      const value = control.value;
      const isValid = /^[a-zA-ZáÁéÉíÍóÓöÖőŐúÚüÜűŰ\s]*$/.test(value);

      return isValid ? null : {'invalidLettersAndSpaces': {value}};
    };
  }

}
