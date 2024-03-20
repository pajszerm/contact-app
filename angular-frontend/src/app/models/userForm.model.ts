import {PhoneNumberFormModel} from "./phoneNumberForm.model";
import {AddressFormModel} from "./addressForm.model";

export interface UserFormModel {
  userName: string,
  name: string,
  mothersName: string,
  email: string,
  birthDate: Date,
  tajNumber: string,
  taxNumber: string,
  phoneNumbers: PhoneNumberFormModel[],
  addresses: AddressFormModel[]
}
