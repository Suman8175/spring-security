package com.suman.springsecurity.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "user_table")
@Data
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_phone_number")
    private long userPhoneNumber;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_dob")
    private LocalDate userDOB;

    @Column(name = "user_password")
    private String userPassword;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private Address address;

}
