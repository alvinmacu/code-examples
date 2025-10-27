package mx.com.alvin.ms.ms_courses.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="courses_users")
@Data
public class CourseUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_id", unique = true)
    private Long userId;

    @Override
    public boolean equals(Object obj) {
        if( this == obj ){
            return true;
        }
        if( !(obj instanceof CourseUser) ){
            return false;
        }
        CourseUser other = (CourseUser) obj;
        return this.userId != null && this.userId.equals(other.userId);
    }
}
