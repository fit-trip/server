package com.example.mapp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer Id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String password;
}
