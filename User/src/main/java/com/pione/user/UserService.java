package com.pione.user;


import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    @Autowired
    private UserRepository repository;


    public void saveUser(Userr userr){
        repository.save(userr);
    }

    public List<Userr> findAllUser(){
        return repository.findAll();
    }

    public void deleteUser(Integer id) {
        repository.deleteById(id);
    }

    public Userr findUserrById(Integer id) {
        return repository.findById(id).orElse(null);
    }
}
