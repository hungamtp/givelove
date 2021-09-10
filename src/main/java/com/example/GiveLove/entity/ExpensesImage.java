package com.example.GiveLove.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExpensesImage {

    @Id
    @GeneratedValue
    private Long id;

    private String image;

    @ManyToOne
    private ExpensesBlock expensesBlock;

}
