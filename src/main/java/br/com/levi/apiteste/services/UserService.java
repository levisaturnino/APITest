package br.com.levi.apiteste.services;

import br.com.levi.apiteste.domian.User;

import java.util.List;

public interface UserService {

    User findById(Integer id);

    List<User> findAll();
}
