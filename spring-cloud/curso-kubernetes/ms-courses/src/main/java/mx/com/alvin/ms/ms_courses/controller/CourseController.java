package mx.com.alvin.ms.ms_courses.controller;

import feign.FeignException;
import jakarta.validation.Valid;
import mx.com.alvin.ms.ms_courses.model.User;
import mx.com.alvin.ms.ms_courses.model.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import mx.com.alvin.ms.ms_courses.service.CourseService;

import java.util.*;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        System.out.println("getAllCourses");
        return ResponseEntity.ok(courseService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable Long id) {
        Optional<Course> course = courseService.findById(id);
        if (course.isPresent()) {
            return ResponseEntity.ok(course.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> addCourse(@Valid @RequestBody Course course, BindingResult result) {
        ResponseEntity<Map<String, String>> errors = validateData(result);
        if (errors != null) return errors;

        return  ResponseEntity.status(HttpStatus.CREATED).body(courseService.save(course));
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> editCourse(@Valid @RequestBody Course course, BindingResult result, @PathVariable Long id) {
        ResponseEntity<Map<String, String>> errors = validateData(result);
        if (errors != null) return errors;

        Optional<Course> optCourseBD = courseService.findById(course.getId());
        if (optCourseBD.isPresent()) {
            Course courseDB = optCourseBD.get();
            courseDB.setName(course.getName());

            return  ResponseEntity.status(HttpStatus.CREATED).body(courseService.save(courseDB));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteCourse(@Valid @RequestBody Course course, BindingResult result) {
        ResponseEntity<Map<String, String>> errors = validateData(result);
        if (errors != null) return errors;

        Optional<Course> courseBD = courseService.findById(course.getId());
        if (courseBD.isPresent()) {
            courseService.deleteById(course.getId());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private static ResponseEntity<Map<String, String>> validateData(BindingResult bindingResult) {
        if( bindingResult.hasErrors() ){
            Map<String,String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errors.put( error.getField(), "El campo " + error.getField() + " " + error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }
        return null;
    }

    @PutMapping("/assign-user/{courseId}")
    public ResponseEntity<?> assignUserToCourse(@RequestBody User user, @PathVariable Long courseId) {
        Optional<User> userBD;
        try {
            userBD = courseService.assignUser(user,courseId);
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body( Collections.singletonMap("message","Error en la comunicación con el microservicio" + e.getMessage()) );
        }

        if (userBD.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(userBD.get().getId());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/create-user/{courseId}")
    public ResponseEntity<?> createUser(@RequestBody User user, @PathVariable Long courseId) {
        Optional<User> userBD;
        try {
            userBD = courseService.createUser(user,courseId);
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body( Collections.singletonMap("message","Error en la comunicación con el microservicio" + e.getMessage()) );
        }

        if (userBD.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(userBD.get().getId());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete-user/{courseId}")
    public ResponseEntity<?> deleteUser(@RequestBody User user, @PathVariable Long courseId) {
        Optional<User> userBD;
        try {
            userBD = courseService.deleteUser(user,courseId);
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body( Collections.singletonMap("message","Error en la comunicación con el microservicio" + e.getMessage()) );
        }

        if (userBD.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(userBD.get().getId());
        }
        return ResponseEntity.notFound().build();
    }
}
