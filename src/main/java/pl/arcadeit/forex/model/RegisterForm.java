package pl.arcadeit.forex.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterForm {

    @NotBlank(message = "{email.required}")
    @Email(message = "{email.invalid}")
    private String email;

    @NotBlank(message = "{first.name.required}")
    @Length(min = 2, max = 25, message = "{first.name.invalid}")
    private String firstName;

    @NotBlank(message = "{last.name.required}")
    @Length(min = 2, max = 50, message = "{last.name.invalid}")
    private String lastName;

    @NotBlank(message = "{password.required}")
    @Length(min = 8, max = 64, message = "{password.invalid}")
    private String password;
}
