package pl.bodzioch.damian.currencies;

import jakarta.validation.constraints.NotEmpty;

record GetCurrentCurrencyValueRequest(
        @NotEmpty(message = "The name field cannot be empty")
        String name,
        @NotEmpty(message = "The currency field cannot be empty")
        @Currency(message = "The currency field has an incorrect value")
        String currency
) {
}
