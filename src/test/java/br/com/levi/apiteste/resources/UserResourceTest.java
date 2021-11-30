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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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
    void findById() {
    }

    @Test
    void findAll() {
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