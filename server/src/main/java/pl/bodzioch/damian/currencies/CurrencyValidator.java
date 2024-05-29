package pl.bodzioch.damian.currencies;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

class CurrencyValidator implements ConstraintValidator<Currency, String> {

    private static final List<String> allowedCurrencies = java.util.Currency.getAvailableCurrencies().stream()
            .map(java.util.Currency::getCurrencyCode)
            .toList();

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return allowedCurrencies.contains(value);
    }
}
