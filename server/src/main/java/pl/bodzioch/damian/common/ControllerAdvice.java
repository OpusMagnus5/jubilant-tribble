package pl.bodzioch.damian.common;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.bodzioch.damian.client.EmptyCurrencyException;
import pl.bodzioch.damian.client.NbpClientException;
import pl.bodzioch.damian.currencies.CurrencyNotFoundException;

import java.util.Optional;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
class ControllerAdvice {

    protected static final String GENERAL_ERROR_MESSAGE = "An unexpected server error has occurred. Please try again later.";

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ MethodArgumentNotValidException.class })
    ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String error = Optional.ofNullable(e.getBindingResult().getFieldError())
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .orElse(GENERAL_ERROR_MESSAGE);
        return new ErrorResponse(error);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ EmptyCurrencyException.class })
    ErrorResponse handleEmptyCurrencyException(EmptyCurrencyException e) {
        return new ErrorResponse("The currency field cannot be empty");
    }

    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler({ NbpClientException.class })
    ErrorResponse handleNbpClientException(NbpClientException e) {
        return new ErrorResponse(GENERAL_ERROR_MESSAGE);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({ CurrencyNotFoundException.class })
    ErrorResponse handleCurrencyNotFoundException(CurrencyNotFoundException e) {
        return new ErrorResponse("No currency exchange rate found in external service");
    }

}
