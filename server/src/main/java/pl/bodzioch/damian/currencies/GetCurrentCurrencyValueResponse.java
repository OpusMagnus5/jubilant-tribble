package pl.bodzioch.damian.currencies;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

record GetCurrentCurrencyValueResponse(
        @JsonProperty("value")
        Double currencyRate
) implements Serializable {
}
