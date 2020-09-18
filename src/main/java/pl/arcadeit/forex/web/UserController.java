package pl.arcadeit.forex.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.arcadeit.forex.model.User;
import pl.arcadeit.forex.services.MapValidationErrorService;
import pl.arcadeit.forex.services.UserService;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    public UserController(UserService userService, MapValidationErrorService mapValidationErrorService) {
        this.userService = userService;
        this.mapValidationErrorService = mapValidationErrorService;
    }

    @PostMapping("")
    public ResponseEntity<?> createNewUser(@Valid @RequestBody User user, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidation(result);
        if (errorMap != null) return errorMap;

        User newUser = userService.saveOrUpdateUser(user);
        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }
}
