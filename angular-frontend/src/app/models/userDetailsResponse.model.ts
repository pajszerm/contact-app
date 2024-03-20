import {UserDetailsModel} from "./userDetails.model";

export interface UserDetailsResponseModel {
  users: UserDetailsModel[],
  pageIndex: number;
  pageSize: number;
  length: number;
}
