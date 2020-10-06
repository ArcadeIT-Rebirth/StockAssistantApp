package pl.arcadeit.forex.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginForm {

    @NotBlank(message = "{email.required}")
    @Email(message = "{email.invalid}")
    private String email;
    @NotBlank(message = "{password.required}")
    @Length(min = 8, max = 64, message = "{login.password.invalid}")
    private String password;
}
