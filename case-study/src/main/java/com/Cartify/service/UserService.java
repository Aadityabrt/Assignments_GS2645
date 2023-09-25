package com.Cartify.service;

import com.Cartify.entity.Users;
import com.Cartify.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
     UserRepo userRepo;

    @Transactional
    public boolean validateUser(String email, String pword){
        Optional<Users> user = userRepo.getUsersByEmailAndPassword(email,pword);
        if(user.isPresent()) return true;
        return false;
    }
    @Transactional
    public void addOrModifyUser(Users user){
        System.out.println(" 🔴 User" + user.getFullName() +"Got Added Successfully 🔴 ");
        userRepo.save(user);
    }

    @Transactional
    public Users getUserByEmailAndPassword(String email,String pswd){
        Optional<Users> user = userRepo.getUsersByEmailAndPassword(email,pswd);
        if(user.isPresent()) {
            return user.get();
        }
        return null;
    }
}
