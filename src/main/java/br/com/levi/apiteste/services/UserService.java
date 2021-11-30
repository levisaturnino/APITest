package br.com.levi.apiteste.services;

import br.com.levi.apiteste.domian.User;
import br.com.levi.apiteste.domian.dtos.UserDTO;

import java.util.List;

public interface UserService {

    User findById(Integer id);

    List<User> findAll();

    User create(UserDTO objDTO);

    User update(UserDTO objDTO);

    void delete(Integer id);
}
