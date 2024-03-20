import {UserDetailsModel} from "./userDetails.model";

export interface UserDetailsResponseModel {
  userDetails: UserDetailsModel[],
  pageIndex: number;
  pageSize: number;
  length: number;
}
