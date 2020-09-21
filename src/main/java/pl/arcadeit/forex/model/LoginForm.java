package pl.arcadeit.forex.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginForm {
    @Email
    private String email;
    @Length(min = 8)
    private String password;
}
