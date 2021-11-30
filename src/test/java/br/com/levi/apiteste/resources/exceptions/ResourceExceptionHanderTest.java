package br.com.levi.apiteste.resources.exceptions;

import br.com.levi.apiteste.domian.dtos.UserDTO;
import br.com.levi.apiteste.services.exceptions.DataIntegrityViolationException;
import br.com.levi.apiteste.services.exceptions.ObjectnotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ResourceExceptionHanderTest {
    @InjectMocks
    private ResourceExceptionHander exceptionHander;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenObjectNotFoundExceptionThenResponseEntity() {

        ResponseEntity<StandardError> responseEntity = exceptionHander
                .objectNotFoundException(new ObjectnotFoundException("Objeto não encontrado"),
                        new MockHttpServletRequest());

        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatus.NOT_FOUND,responseEntity.getStatusCode());
        assertEquals(ResponseEntity.class,responseEntity.getClass());
        assertEquals(StandardError.class,responseEntity.getBody().getClass());
        assertEquals("Objeto não encontrado",responseEntity.getBody().getError());
        assertEquals(404,responseEntity.getBody().getStatus());
        assertNotEquals("/user/2",responseEntity.getBody().getPath());
        assertNotEquals(LocalDateTime.now(),responseEntity.getBody().getTimestamp());

    }

    @Test
    void whenDataIntegrityViolationExceptionThenReturnResponseEntity() {

        ResponseEntity<StandardError> responseEntity = exceptionHander
                .dataIntegrityViolationException(new DataIntegrityViolationException("E-mail já existem no sistema"),
                        new MockHttpServletRequest());

        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatus.BAD_REQUEST,responseEntity.getStatusCode());
        assertEquals(ResponseEntity.class,responseEntity.getClass());
        assertEquals(StandardError.class,responseEntity.getBody().getClass());
        assertEquals("E-mail já existem no sistema",responseEntity.getBody().getError());
        assertEquals(400,responseEntity.getBody().getStatus());
    }
}