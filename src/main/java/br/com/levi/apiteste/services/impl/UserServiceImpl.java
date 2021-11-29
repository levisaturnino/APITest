package br.com.levi.apiteste.services.impl;

import br.com.levi.apiteste.domian.User;
import br.com.levi.apiteste.repositories.UserRepository;
import br.com.levi.apiteste.services.UserService;
import br.com.levi.apiteste.services.exceptions.ObjectnotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ObjectnotFoundException("Object user não encontrado!"));
    }
}
