package mx.com.alvin.ms.ms_users.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotEmpty
    @NotNull
    private String name;

    @NotEmpty
    @NotNull
    @Email(message = "El formato de email es incorrecto")
    @Column(unique = true)
    private String email;

    @NotNull
    @NotEmpty
    private String password;
}
