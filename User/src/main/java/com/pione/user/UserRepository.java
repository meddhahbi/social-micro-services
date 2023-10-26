package com.pione.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<Userr,Integer> {
    List<Userr> findAllByBlogId(Integer blogId);
}
