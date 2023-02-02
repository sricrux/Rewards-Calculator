package com.merchant.rewards.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "LOYAL_USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_NUM")
    private Long userNum;
    @Column(name = "USER_NAME")
    private String userName;

}
