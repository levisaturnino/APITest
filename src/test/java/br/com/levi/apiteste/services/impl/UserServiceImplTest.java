package br.com.levi.apiteste.services.impl;

import br.com.levi.apiteste.domian.User;
import br.com.levi.apiteste.domian.dtos.UserDTO;
import br.com.levi.apiteste.repositories.UserRepository;
import br.com.levi.apiteste.services.exceptions.DataIntegrityViolationException;
import br.com.levi.apiteste.services.exceptions.ObjectnotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    private static final Integer ID = 1;
    private static final String NAME = "Levi Saturnino";
    private static final String EMAIL = "levisaturnio@gmail.com";
    private static final String PASSWORD = "123";
    private static final int INDEX = 0;

    @InjectMocks
    private UserServiceImpl userService;
 
    @Mock
    private UserRepository userRepository;
    
    @Mock
    private ModelMapper mapping;
    
    private User user;
    
    private UserDTO userDTO;
    
    private Optional<User> optionalUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUsers();
    }

    @Test
    void findById() {

        when(userRepository.findById(anyInt())).thenReturn(optionalUser);

        User response = userService.findById(ID);

        assertNotNull(response);

        assertEquals(User.class,response.getClass());
        assertEquals(ID,response.getId());
        assertEquals(NAME,response.getName());
        assertEquals(EMAIL,response.getEmail());
    }

    @Test
    void whenFindByIdThenReturnAndOnbjectNotFoundException(){
        when(userRepository.findById(anyInt())).thenThrow(new ObjectnotFoundException("Objeto não encontrado"));
        try{
            userService.findById(ID);
        }catch (Exception ex){
            assertEquals(ObjectnotFoundException.class,ex.getClass());
            assertEquals("Objeto não encontrado",ex.getMessage());
        }
    }

    @Test
    void whenFindAllThenReturnAnListOfUsers() {
        when(userRepository.findAll()).thenReturn(List.of(user,user));
        List<User> response = userService.findAll();

        assertNotNull(response);
        assertEquals(2,response.size());
        assertEquals(User.class,response.get(INDEX).getClass());
        assertEquals(ID,response.get(INDEX).getId());
        assertEquals(NAME,response.get(INDEX).getName());
        assertEquals(EMAIL,response.get(INDEX).getEmail());
        assertEquals(PASSWORD,response.get(INDEX).getPassword());

    }

    @Test
    void whenCreateThenReturnSuccess() {
        when(userRepository.findByEmail(any())).thenReturn(optionalUser);
        try{
            optionalUser.get().setId(2);
              userService.create(userDTO);

        }catch (Exception ex){
            assertEquals(DataIntegrityViolationException.class,ex.getClass());
            assertEquals("E-mail já cadastrado no sistema!",ex.getMessage());
        }

    }

    @Test
    void whenCreateThenReturnDataIntegrityViolationException() {
        when(userRepository.save(any())).thenReturn(new DataIntegrityViolationException("E-mail já existente no sistema"));

        User response = userService.create(userDTO);

        assertNotNull(response);

        assertEquals(User.class,response.getClass());
        assertEquals(ID,response.getId());
        assertEquals(NAME,response.getName());
        assertEquals(EMAIL,response.getEmail());
        assertEquals(PASSWORD,response.getPassword());

    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
    
    private void startUsers(){
        user = new User(ID,NAME,EMAIL,PASSWORD);
        userDTO = new UserDTO(ID,NAME,EMAIL,PASSWORD);
        optionalUser =  Optional.of(new User(ID,NAME,EMAIL,PASSWORD));
    }
}