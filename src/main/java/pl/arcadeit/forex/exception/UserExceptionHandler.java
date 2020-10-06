package pl.arcadeit.forex.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.arcadeit.forex.controller.UserController;
import pl.arcadeit.forex.model.ErrorMessage;

import javax.annotation.Priority;
import java.util.List;
import java.util.stream.Collectors;

@Priority(1)
@RestControllerAdvice(assignableTypes = UserController.class)
public class UserExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorMessage handleMethodValidationException(final MethodArgumentNotValidException exception) {
        return ErrorMessage.builder()
                .message("Validation failed.")
                .details(getExceptionMessages(exception))
                .build();
    }

    private List<String> getExceptionMessages(final MethodArgumentNotValidException exception) {
        return getAllErrorsFromException(exception).stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
    }

    private List<ObjectError> getAllErrorsFromException(final MethodArgumentNotValidException exception) {
        return exception.getBindingResult().getAllErrors();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserException.class)
    public ErrorMessage handleUserException(final UserException exception) {
        return ErrorMessage.builder()
                .message("User cannot be validated")
                .details(List.of(exception.getMessage()))
                .build();
    }
}
