import {PhoneNumberFormModel} from "./phoneNumberForm.model";
import {AddressFormModel} from "./addressForm.model";

export interface UserFormModel {
  userName: String,
  name: String,
  mothersName: String,
  email: String,
  birthDate: Date,
  tajNumber: String,
  taxNumber: String,
  phoneNumbers: PhoneNumberFormModel[],
  addresses: AddressFormModel[]
}
