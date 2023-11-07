package com.pione.user;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Transient;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Userr {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String email;
    private String role;
    private boolean isVerified;
    private String password;
    private Integer blogId;

    @Transient
    private String rawPassword;

    public void setRawPassword(String rawPassword) {
        this.rawPassword = rawPassword;
    }

    public void encryptPassword() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(rawPassword);
    }


}
