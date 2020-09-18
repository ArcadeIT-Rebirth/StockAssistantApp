package pl.arcadeit.forex.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "User Identifier is required")
    @Size(min = 4, max = 5, message = "Pleas use 4-5 characters")
    @Column(updatable = false, unique = true)
    private String userId;

    @NotBlank(message="User name is required")
    @Size(min = 5, max = 12, message = "Pleas use min 5-12 characters")
    @Column(updatable = false, unique = true)
    private String name;

    @NotBlank(message="Password name is required")
    @Size(min = 8, max = 15, message = "Pleas use min 8-15 characters")
    private String password;

    @NotBlank(message="Email name is required")
    @Email(message = "Email should be valid")
    private String email;

    @JsonFormat(pattern = "yyy-mm-dd")
    private Date created_At;

    @JsonFormat(pattern = "yyy-mm-dd")
    private Date update_At;

    @PrePersist
    protected void onCreate() {
        this.created_At = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.update_At = new Date();
    }
}
