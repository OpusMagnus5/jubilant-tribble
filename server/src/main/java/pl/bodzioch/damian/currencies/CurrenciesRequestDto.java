package pl.bodzioch.damian.currencies;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

record CurrenciesRequestDto(
        String currency,
        String name,
        LocalDateTime date,
        @JsonProperty("value")
        Double exchangeRate
) {

    CurrenciesRequestDto(CurrenciesRequest entity) {
        this(
                entity.getCurrency(),
                entity.getName(),
                entity.getDate(),
                entity.getExchangeRate()
        );
    }
}
