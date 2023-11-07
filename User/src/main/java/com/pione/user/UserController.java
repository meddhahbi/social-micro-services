package com.pione.user;


import com.pione.user.confimEmail.ConfirmationToken;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.List;
import org.springframework.mail.SimpleMailMessage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/users")
public class UserController {


    @Autowired
    private UserService service;
    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepository userRepository;




    @PostMapping("/register")
    public ResponseEntity<Userr> save(@RequestBody Userr userr){
    Userr userExisted=service.findUserByEmail(userr.getEmail());
        if(userExisted==null){
        userr.setRole("user");
        userr.setRawPassword(userr.getPassword());
        userr.setVerified(false);



        // Encrypt and set the password
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userr.setPassword(passwordEncoder.encode(userr.getRawPassword()));

        Userr addedUser= service.saveUser(userr);


            ConfirmationToken confirmationToken = new ConfirmationToken(userr);
            confirmationTokenRepository.save(confirmationToken);

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(userr.getEmail());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setFrom("3asbatoxic@gmail.com");
            mailMessage.setText("To confirm your account, please click here : "
                    +"http://localhost:8086/api/v1/users/confirm-account?token="+confirmationToken.getConfirmationToken());
            emailService.sendEmail(mailMessage);








        return ResponseEntity.status(HttpStatus.CREATED).body(addedUser);
        }
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userExisted);

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
    public ResponseEntity<Userr> updateTicket(@PathVariable Integer id, @RequestBody Userr updatedUser) {
        Userr existingUser = service.findUserrById(id);
        if (existingUser != null) {
            updatedUser.setId(id); // Make sure the ID matches
           Userr updatedUserr= service.saveUser(updatedUser);
            return ResponseEntity.ok(updatedUserr);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/login")
    public ResponseEntity<Userr> login(@RequestBody Userr loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
         if (service.authenticateUser(email, password)) {
            // Password is correct, user is authenticated
            return ResponseEntity.status(HttpStatus.ACCEPTED).body( userRepository.findByEmail(email));
        } else {
            // Password is incorrect, authentication failed
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Userr>> findAllCategories(){
        return ResponseEntity.ok(service.findAllUser());
    }








    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity confirmUserAccount( @RequestParam("token")String confirmationToken)
    {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null)
        {
            Userr user = userRepository.findByEmail(token.getUserEntity().getEmail());
            user.setVerified(true);
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("accountVerified successfully!");


        }
        else
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The link is invalid or broken!!");


        }



}

    @GetMapping("/blog/{blog-id}")
    public ResponseEntity<List<Userr>>findAllUsers(
            @PathVariable("blog-id") Integer blogId
    ){
        return ResponseEntity.ok(service.findAllUsersByBlog(blogId));
    }

}

