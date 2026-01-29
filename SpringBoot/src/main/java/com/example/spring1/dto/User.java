package com.example.spring1.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Data
@Table(name = "user")
@Entity //BEAN: new object
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int old;
//    @Column(name = "db.name")
    @Column(unique = true)
    private String username;
    private String password;
    private String homeAddress;

}
