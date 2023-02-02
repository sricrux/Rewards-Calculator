package com.merchant.rewards.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "TRANSACTION")
public class Transaction {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "TRX_NUM")
    private Long transactionNum;

    @Column(name="USER_NUM")
    private Long userNum;

    @Column(name = "TRX_DATE")
    private Timestamp transactionDate;

    @Column(name = "TOTAL")
    private double transactionTotal;
}
