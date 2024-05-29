package pl.bodzioch.damian.client;

public class EmptyCurrencyException extends RuntimeException {

    EmptyCurrencyException(String message) {
        super(message);
    }
}
