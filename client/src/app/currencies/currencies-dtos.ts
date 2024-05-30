export interface GetCurrentCurrencyValueRequest {
  name: string,
  currency: string
}

export interface GetCurrentCurrencyValueResponse {
  value: number
}

export interface CurrenciesRequestDto {
  currency: string,
  name: string,
  date: Date,
  value: number
}

export interface GetCurrenciesRequestsResponse {
  requests: CurrenciesRequestDto[]
}
