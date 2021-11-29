package br.com.levi.apiteste.repositories;

import br.com.levi.apiteste.domian.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

}
