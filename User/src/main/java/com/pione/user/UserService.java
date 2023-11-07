package com.pione.user;


import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.List;

@Service
public class UserService {


    @Autowired
    private UserRepository repository;



    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public boolean authenticateUser(String email, String rawPassword) {
        Userr user = repository.findByEmail(email);

        if (user != null && passwordEncoder.matches(rawPassword, user.getPassword())) {
            // Passwords match
            return true;
        }

        return false;
    }
    public Userr saveUser(Userr userr){
        return repository.save(userr);
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
    public Userr findUserByEmail(String email) {
        return repository.findByEmail(email);
    }
    public List<Userr> findAllUsersByBlog(Integer blogId) {
        return repository.findAllByBlogId(blogId);
    }
}
