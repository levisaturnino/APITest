package br.com.levi.apiteste.services.impl;

import br.com.levi.apiteste.domian.User;
import br.com.levi.apiteste.domian.dtos.UserDTO;
import br.com.levi.apiteste.repositories.UserRepository;
import br.com.levi.apiteste.services.UserService;
import br.com.levi.apiteste.services.exceptions.DataIntegrityViolationException;
import br.com.levi.apiteste.services.exceptions.ObjectnotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapping;

    @Override
    public User findById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ObjectnotFoundException("Object user não encontrado!"));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User create(UserDTO objDTO) {
        findByEmail(objDTO);
        return userRepository.save(mapping.map(objDTO,User.class));
    }

    @Override
    public User update(Integer id, UserDTO objDTO) {
        findByEmail(objDTO);
        return userRepository.save(mapping.map(objDTO,User.class));

    }

    private void findByEmail(UserDTO objDTO){
        Optional<User> user = userRepository.findByEmail(objDTO.getEmail());
        if(user.isPresent() && !user.get().getId().equals(objDTO.getId())){
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
        }
    }
}
