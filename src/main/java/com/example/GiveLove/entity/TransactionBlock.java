package com.example.GiveLove.entity;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
