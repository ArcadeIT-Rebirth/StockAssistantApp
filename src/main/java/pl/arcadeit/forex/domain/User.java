package pl.arcadeit.forex.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
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
    @NotNull(message = "{email.required}")
    @Email(message = "{email.invalid}")
    @Column(name = "email")
    private String email;

    @NotNull(message = "first name is required")
    @Length(min = 2, max = 25, message = "first name should contain between 2 and 25 characters")
    @Column(name = "first_name")
    private String firstName;

    @NotNull(message = "last name is required")
    @Length(min = 2, max = 50, message = "last name should contain between 2 and 50 characters")
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message = "password is required")
    @Length(min = 8, max = 64, message = "password should contain between 8 and 64 characters")
    @Column(name = "password")
    private String password;

    @NotNull(message = "role is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole role;

    @Column(name = "wallet_number")
    private String walletNumber;

}
