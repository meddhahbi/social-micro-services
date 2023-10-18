package com.pione.user;


import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/users")
public class UserController {


    @Autowired
    private UserService service;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> save(@RequestBody Userr userr){
        service.saveUser(userr);
        return ResponseEntity.status(HttpStatus.CREATED).body("Category saved successfully!");

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteCategory(@PathVariable Integer id){
        service.deleteUser(id);
        return ResponseEntity.status(HttpStatus.CREATED).body("Category deleted successfully!");
    }
    @GetMapping("/{id}")
    public ResponseEntity<Userr> findUserrById(@PathVariable Integer id) {
        Userr user = service.findUserrById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateTicket(@PathVariable Integer id, @RequestBody Userr updatedUser) {
        Userr existingUser = service.findUserrById(id);
        if (existingUser != null) {
            updatedUser.setId(id); // Make sure the ID matches
            service.saveUser(updatedUser);
            return ResponseEntity.ok("Ticket with ID " + id + " updated successfully!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Userr>> findAllCategories(){
        return ResponseEntity.ok(service.findAllUser());
    }

}

