package br.com.levi.apiteste.resources;

import br.com.levi.apiteste.domian.User;
import br.com.levi.apiteste.domian.dtos.UserDTO;
import br.com.levi.apiteste.repositories.UserRepository;
import br.com.levi.apiteste.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class UserResourceTest {

    private static final Integer ID = 1;
    private static final String NAME = "Levi Saturnino";
    private static final String EMAIL = "levisaturnio@gmail.com";
    private static final String PASSWORD = "123";
    private static final int INDEX = 0;
    public static final String E_MAIL_JA_CADASTRADO_NO_SISTEMA = "E-mail já cadastrado no sistema!";
    public static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado";

    private User user;

    private UserDTO userDTO;

    @InjectMocks
    UserResource userResource;

    @Mock
    private UserServiceImpl userService;

    @Mock
    private ModelMapper mapping;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUsers();
    }

    @Test
    void whenFindByIdThenReturnSuccess() {
        when(userService.findById(anyInt())).thenReturn(user);
        when(mapping.map(any(),any())).thenReturn(userDTO);

        ResponseEntity<UserDTO> responseEntity = userResource.findById(ID);

        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        assertEquals(ResponseEntity.class,responseEntity.getClass());
        assertEquals(UserDTO.class,responseEntity.getBody().getClass());
        assertEquals(ID,responseEntity.getBody().getId());
        assertEquals(NAME,responseEntity.getBody().getName());
        assertEquals(EMAIL,responseEntity.getBody().getEmail());
        assertEquals(PASSWORD,responseEntity.getBody().getPassword());
    }

    @Test
    void whenFindAllThenReturnSuccess() {
        when(userService.findAll()).thenReturn(List.of(user));
        when(mapping.map(any(),any())).thenReturn(userDTO);

        ResponseEntity<List<UserDTO>> responseEntity = userResource.findAll();

        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        assertEquals(ResponseEntity.class,responseEntity.getClass());
        assertEquals(ArrayList.class,responseEntity.getBody().getClass());
        assertEquals(UserDTO.class,responseEntity.getBody().get(INDEX).getClass());

        assertEquals(ID,responseEntity.getBody().get(INDEX).getId());
        assertEquals(NAME,responseEntity.getBody().get(INDEX).getName());
        assertEquals(EMAIL,responseEntity.getBody().get(INDEX).getEmail());
        assertEquals(PASSWORD,responseEntity.getBody().get(INDEX).getPassword());
    }

    @Test
    void create() {
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
    }
}