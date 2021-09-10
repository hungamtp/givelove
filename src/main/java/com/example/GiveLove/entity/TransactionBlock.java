package com.example.GiveLove.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionBlock {

    @Id
    private String hash;

    private String previousHash;

    private LocalDate datetime;

    private int nonce;

    private Long money;
    private LocalDate date;

    @ManyToOne
    private Users user;

    @ManyToOne
    private Campaign campaign;
}
