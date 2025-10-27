package mx.com.alvin.ms.ms_courses.model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class User {
    private Long id;
    private String name;
    private String email;
    private String password;
}
