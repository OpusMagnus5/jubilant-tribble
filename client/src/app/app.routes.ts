import { Routes } from '@angular/router';
import {GetCurrencyRateComponent} from "./currencies/get-currency-rate.component";
import {RequestListComponent} from "./currencies/request-list.component";

export const GET_CURRENCY_RATE_PATH: string = "currencies/rate"
export const REQUESTS_PATH: string = "currencies/requests"

export const routes: Routes = [
  {
    path: GET_CURRENCY_RATE_PATH,
    component: GetCurrencyRateComponent
  },
  {
    path: REQUESTS_PATH,
    component: RequestListComponent
  }
];
