package mx.com.alvin.ms.ms_courses.service;

import mx.com.alvin.ms.ms_courses.model.User;
import mx.com.alvin.ms.ms_courses.model.entity.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<Course> findAll();
    Optional<Course> findById(Long id);
    Course save(Course course);
    void deleteById(Long id);

    Optional<User> assignUser( User user, Long courseId );
    Optional<User> createUser( User user, Long courseId );
    Optional<User> deleteUser( User user, Long courseId );
}
