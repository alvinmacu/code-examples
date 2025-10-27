package mx.com.alvin.ms.ms_users;

import jakarta.validation.Valid;
import mx.com.alvin.ms.ms_users.model.entity.User;
import mx.com.alvin.ms.ms_users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> list(){
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detail(@PathVariable Long id){
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok( user.get() );
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody User user, BindingResult bindingResult){
        ResponseEntity<Map<String, String>> errors = validateData(bindingResult);
        if (errors != null) return errors;

        if( userService.getUserByEmail(user.getEmail()).isPresent() ){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Collections.singletonMap("mensaje","Ya existe un email registrado"));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(user));
    }



    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody User user, BindingResult bindingResult, @PathVariable Long id){

        ResponseEntity<Map<String, String>> errors = validateData(bindingResult);
        if (errors != null) return errors;

        Optional<User> userOptional = userService.getUserById(id);
        if (userOptional.isPresent()) {
            User u = userOptional.get();

            if( !user.getEmail().equalsIgnoreCase( u.getEmail() ) && userService.getUserByEmail(user.getEmail()).isPresent() ){
                return ResponseEntity.status(HttpStatus.CONFLICT).body(Collections.singletonMap("mensaje","Ya existe un email registrado"));
            }
            
            u.setName(user.getName());
            u.setEmail(user.getEmail());
            u.setPassword(user.getPassword());

            return ResponseEntity.status(HttpStatus.CREATED).body( userService.saveUser(u));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<User> userOptional = userService.getUserById(id);
        if (userOptional.isPresent()) {
            userService.deleteUser(id);
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
}
