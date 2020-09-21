package pl.arcadeit.forex.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.arcadeit.forex.controller.UserController;
import pl.arcadeit.forex.model.ErrorMessage;

import java.util.List;

@RestControllerAdvice(assignableTypes = UserController.class)
public class UserExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserException.class)
    public ErrorMessage handleUserException(final UserException exception) {
        return ErrorMessage.builder()
                .message("User cannot be validated")
                .details(List.of(exception.getMessage()))
                .build();
    }
}
