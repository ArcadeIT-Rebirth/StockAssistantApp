package pl.arcadeit.forex.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.arcadeit.forex.model.ErrorMessage;

import javax.annotation.Priority;
import java.util.List;

@Priority(2)
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ErrorMessage handleUnknownException(final Exception exception) {
        return ErrorMessage.builder()
                .message("Something went wrong")
                .details(List.of(exception.getClass().toGenericString()))
                .build();
    }
}
