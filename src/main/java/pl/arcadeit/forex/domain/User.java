package pl.arcadeit.forex.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/*
    User class contains all necessary fields and some constraints.
    Annotations Data, NoArgsConstructor,AllArgsConstructor, Builder are from Lombok plugin.
 */

@Entity(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @NotBlank(message = "{email.required}")
    @Email(message = "{email.invalid}")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "{first.name.required}")
    @Length(min = 2, max = 25, message = "{first.name.invalid}")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "{last.name.required}")
    @Length(min = 2, max = 50, message = "{last.name.invalid}")
    @Column(name = "last_name")
    private String lastName;

    @NotBlank(message = "{password.required}")
    @Length(min = 8, max = 64, message = "{password.invalid}")
    @Column(name = "password")
    private String password;

    @NotNull(message = "{role.required}")
    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private UserRole role;

    @Column(name = "wallet_number")
    private String walletNumber;

}
