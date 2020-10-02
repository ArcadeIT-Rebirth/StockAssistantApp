package pl.arcadeit.forex.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginForm {

    @NotNull(message = "{email.required}")
    @Email(message = "{email.invalid}")
    private String email;
    @NotNull(message = "{password.required}")
    @Length(min = 8, max = 64, message = "{login.password.invalid}")
    private String password;
}
