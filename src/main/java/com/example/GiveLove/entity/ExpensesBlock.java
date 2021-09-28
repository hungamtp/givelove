package com.example.GiveLove.entity;

import lombok.*;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExpensesBlock {

    @Id
    private String hash;
    private String previousHash;
    private LocalDate datetime;

    private int nonce;
    private Long money;
    private String description;
    private LocalDate date;

    @OneToMany(mappedBy = "expensesBlock")
    private List<ExpensesImage> images;

    @ManyToOne
    private Campaign campaign;

    public String toData(){
        return description+money+date;
    }
}
