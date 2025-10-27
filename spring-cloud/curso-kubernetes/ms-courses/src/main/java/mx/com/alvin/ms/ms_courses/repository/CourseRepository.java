package mx.com.alvin.ms.ms_courses.repository;

import mx.com.alvin.ms.ms_courses.model.entity.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {

}
