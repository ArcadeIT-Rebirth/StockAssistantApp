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
public class UserModel {

    @Length(min = 2, max = 25, message = "{first.name.invalid}")
    private String firstName;

    @Length(min = 2, max = 50, message = "{last.name.invalid}")
    private String lastName;

    @NotBlank(message = "{email.required}")
    @Email(message = "{email.invalid}")
    private String email;

}
