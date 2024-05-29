package pl.bodzioch.damian.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static pl.bodzioch.damian.common.ControllerAdvice.GENERAL_ERROR_MESSAGE;

@Slf4j
@Order
@RestControllerAdvice
class GlobalControllerAdvice {

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ErrorResponse handleUserAppException(Exception e) {
        log.error(e.getMessage(), e);
        return new ErrorResponse(GENERAL_ERROR_MESSAGE);
    }
}
