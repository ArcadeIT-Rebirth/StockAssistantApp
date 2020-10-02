package pl.arcadeit.forex.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {

    @NotNull(message = "first name is required")
    @Length(min = 2, max = 25, message = "first name should contain between 2 and 50 characters")
    private String firstName;

    @NotNull(message = "last name is required")
    @Length(min = 2, max = 50, message = "last name should contain between 2 and 50 characters")
    private String lastName;

    @NotNull(message = "email is required")
    @Email(message = "email format is invalid")
    private String email;

}
