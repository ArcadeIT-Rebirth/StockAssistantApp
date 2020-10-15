package pl.arcadeit.forex.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.arcadeit.forex.domain.User;
import pl.arcadeit.forex.model.RegisterForm;
import pl.arcadeit.forex.model.RegisterFormConverter;
import pl.arcadeit.forex.model.UserDTO;
import pl.arcadeit.forex.model.UserModel;
import pl.arcadeit.forex.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

/*
    User controller.
    If userDTO is highlighted - build project. Mapstruct instance must be available in target dir.
 */

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final UserDTO userDTO;
    private final RegisterFormConverter converter;

    public UserController(final UserService userService, final UserDTO userDTO,
                          final RegisterFormConverter converter) {
        this.userService = userService;
        this.userDTO = userDTO;
        this.converter = converter;
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public UserModel registerNewUser(@RequestBody @Valid final RegisterForm user) {
        return convertToModel(createUser(user));
    }

    private UserModel convertToModel(final User user) {
        return userDTO.userToUserModel(user);
    }

    private User createUser(final RegisterForm user) {
        return userService.createNewUser(converter.convertToUser(user));
    }

    @GetMapping("/list")
    public List<User> getUsers() {
        return userService.getAll();
    }

    @GetMapping("/get-data")
    public UserModel getUser(final Principal principal) {
        return convertToModel(userService.getUserByEmail(principal.getName()));
    }

    @PatchMapping("/{email}")
    public void updateData(@PathVariable final String email, @RequestBody @Valid final UserModel userModel) {
        userService.update(email, userDTO.userModelToUser(userModel));
    }
}
