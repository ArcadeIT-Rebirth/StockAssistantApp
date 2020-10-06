package pl.arcadeit.forex.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;

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
    @Email(message = "Email address is invalid.")
    @Column(name = "email")
    private String email;

    @Length(max = 25, message = "First name should contain up to 25 characters.")
    @Column(name = "first_name")
    private String firstName;

    @Length(min = 2, max = 50, message = "Last name should contain 2 to 50 characters.")
    @Column(name = "last_name")
    private String lastName;

    @Length(min = 8, message = "Password should contain at least 8 characters.")
    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private UserRole role;

    @Column(name = "wallet_number")
    private String walletNumber;

}
