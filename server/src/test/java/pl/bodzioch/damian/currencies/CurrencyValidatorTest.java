package pl.bodzioch.damian.currencies;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CurrencyValidatorTest {

    private static final String EUR_CURRENCY = "EUR";
    private static final String INVALID_CURRENCY = "EURO";

    private final CurrencyValidator currencyValidator = new CurrencyValidator();

    @Test
    void When_currency_is_valid_Should_return_true() {
        boolean valid = currencyValidator.isValid(EUR_CURRENCY, null);
        assertTrue(valid);
    }

    @Test
    void When_currency_is_empty_Should_return_false() {
        boolean valid = currencyValidator.isValid("", null);
        assertFalse(valid);
    }

    @Test
    void When_currency_is_null_Should_return_false() {
        boolean valid = currencyValidator.isValid(null, null);
        assertFalse(valid);
    }

    @Test
    void When_currency_is_incorrect_Should_return_false() {
        boolean valid = currencyValidator.isValid(INVALID_CURRENCY, null);
        assertFalse(valid);
    }
}
