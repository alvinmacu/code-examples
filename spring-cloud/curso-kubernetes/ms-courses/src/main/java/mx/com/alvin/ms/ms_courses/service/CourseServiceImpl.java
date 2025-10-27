package mx.com.alvin.ms.ms_courses.service;

import mx.com.alvin.ms.ms_courses.client.UserClientRest;
import mx.com.alvin.ms.ms_courses.model.User;
import mx.com.alvin.ms.ms_courses.model.entity.Course;
import mx.com.alvin.ms.ms_courses.model.entity.CourseUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import mx.com.alvin.ms.ms_courses.repository.CourseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService{
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserClientRest userClientRest;

    @Override
    @Transactional(readOnly = true)
    public List<Course> findAll() {
        return (List<Course>) courseRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Course> findById(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    @Transactional
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<User> assignUser(User user, Long courseId) {
        Optional<Course> optCourse = courseRepository.findById(courseId);
        if (optCourse.isPresent()) {
            User userMsvc = userClientRest.detail(user.getId());

            Course course = optCourse.get();
            CourseUser courseUser = new CourseUser();
            courseUser.setUserId( userMsvc.getId() );

            course.addCourseUser(courseUser);
            courseRepository.save(course);
            return Optional.of(userMsvc);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<User> createUser(User user, Long courseId) {
        Optional<Course> optCourse = courseRepository.findById(courseId);
        if (optCourse.isPresent()) {
            User newUser = userClientRest.create(user);

            Course course = optCourse.get();
            CourseUser courseUser = new CourseUser();
            courseUser.setUserId( newUser.getId() );

            course.addCourseUser(courseUser);
            courseRepository.save(course);
            return Optional.of(newUser);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<User> deleteUser(User user, Long courseId) {
        Optional<Course> optCourse = courseRepository.findById(courseId);
        if (optCourse.isPresent()) {
            User userMsvc = userClientRest.detail(user.getId());

            Course course = optCourse.get();
            CourseUser courseUser = new CourseUser();
            courseUser.setUserId( userMsvc.getId() );

            course.removeCourseUser(courseUser);
            courseRepository.save(course);
            return Optional.of(userMsvc);
        }
        return Optional.empty();
    }
}
