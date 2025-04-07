package com.suman.springsecurity.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "address_table")
@Data
public class Address {

    @Column(name = "address_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addressId;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "house_number")
    private int houseNumber;
}
