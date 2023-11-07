package com.pione.user;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;


public interface UserRepository extends JpaRepository<Userr,Integer> {
    public Userr findByEmail(String email);
    public Userr findByEmailAndPassword(String email,String password);
    List<Userr> findAllByBlogId(Integer blogId);

}
