package pl.bodzioch.damian.currencies;

import java.io.Serializable;
import java.util.List;

record GetCurrenciesRequestsResponse(List<CurrenciesRequestDto> requests) implements Serializable {
}
