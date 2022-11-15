package com.example.mapp.entity;

import javax.persistence.*;

@Entity
@Table(name = "Location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ManyToOne
    @JoinColumn(name = "routeInfoId")
    Integer id;

    @Column(name = "name")
    String name;

    @Column(name = "x")
    Double x;

    @Column(name = "y")
    Double y;
}