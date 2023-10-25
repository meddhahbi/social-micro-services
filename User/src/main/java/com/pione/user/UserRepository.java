package com.pione.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Userr,Integer> {
    public Userr findByEmail(String email);
    public Userr findByEmailAndPassword(String email,String password);
}
