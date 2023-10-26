package com.pione.user;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Userr {

    @Id
    @GeneratedValue
    private Integer id;

    private String namee;

    private String emaill;

    private String passwordd;

    private Integer blogId ;



}
