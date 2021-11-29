package br.com.levi.apiteste.resources;

import br.com.levi.apiteste.domian.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Integer id){

        return ResponseEntity.ok(new User(id,"Levi","levi@gmail.com","123"));
    }
}
