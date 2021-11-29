package br.com.levi.apiteste.resources;

import br.com.levi.apiteste.domian.User;
import br.com.levi.apiteste.domian.dtos.UserDTO;
import br.com.levi.apiteste.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    public static final String ID = "/{id}";

    @Autowired
    private ModelMapper mapping;

    @Autowired
    private UserService userService;

    @GetMapping(value = ID)
    public ResponseEntity<UserDTO> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(mapping.map(userService.findById(id), UserDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){

        return ResponseEntity.ok().body(userService
                .findAll().stream().map( x -> mapping.map(x,UserDTO.class)).collect(Collectors.toList()));
    }
    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO objDTO){
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path(ID).buildAndExpand(userService.create(objDTO).getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = ID)
    public ResponseEntity<UserDTO> update(@PathVariable Integer id,@RequestBody UserDTO objDTO){
        objDTO.setId(id);
        return ResponseEntity.ok().body(mapping.map(userService.update(id,objDTO), UserDTO.class));
    }

    @DeleteMapping(value = ID)
    public ResponseEntity<UserDTO> delete(@PathVariable Integer id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
