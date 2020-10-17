package pl.arcadeit.forex.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChangePasswordForm {

    @NotBlank(message = "${password.required}")
    @Length(min = 8, message = "${password.invalid}")
    private String currentPassword;

    @NotBlank(message = "${password.required}")
    @Length(min = 8, message = "${password.invalid}")
    private String newPassword;
}
