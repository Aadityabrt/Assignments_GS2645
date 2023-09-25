package com.Cartify.repository;

import com.Cartify.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepo extends JpaRepository<Users,Integer> {
    Optional<Users> getUsersByEmailAndPassword(String email, String password);
    //Optional<Users> getUsersByEmailAndPassword(String email,String password);
}
