package pl.bodzioch.damian.currencies;

public class CurrencyNotFoundException extends RuntimeException {
    CurrencyNotFoundException(String message) {
        super(message);
    }
}
