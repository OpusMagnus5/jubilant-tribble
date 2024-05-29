package pl.bodzioch.damian.client;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

record NpbRatesData(
        String currency,
        @JsonProperty("code")
        String currencyCode,
        @JsonProperty("mid")
        Double exchangeRate
) implements Serializable {
}
