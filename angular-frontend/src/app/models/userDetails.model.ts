import {UserAddressDetailsModel} from "./userAddressDetails.model";
import {PhoneNumberDetailsModel} from "./phoneNumberDetails.model";

export interface UserDetailsModel {
  username: string,
  name: string,
  mothersName: string,
  email: string,
  birthDate: string,
  tajNumber: string,
  taxNumber: string,
  addresses: UserAddressDetailsModel[],
  phoneNumbers: PhoneNumberDetailsModel[]
}
