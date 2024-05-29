import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {GetCurrentCurrencyValueRequest, GetCurrentCurrencyValueResponse} from "./currencies-dtos";
import {SERVER_URL} from "../shared/server-config";
import {Observable} from "rxjs";

@Injectable({providedIn: "root"})
export class CurrenciesHttpService {

  private readonly CURRENCIES_PATH: string = 'currencies'

  constructor(
    private http: HttpClient
  ) {
  }

  getCurrentCurrencyValue(request: GetCurrentCurrencyValueRequest): Observable<GetCurrentCurrencyValueResponse> {
    return this.http.post<GetCurrentCurrencyValueResponse>(
      SERVER_URL + this.CURRENCIES_PATH + '/get-current-currency-value-command',
      request
    )
  }
}
