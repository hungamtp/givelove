package com.example.GiveLove.entity;

import lombok.*;

import java.util.List;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Campaign {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long money;
    private String location;
    private String state;

    @ManyToMany(mappedBy = "campaigns")
    private List<Users> sponsors;

    @ManyToMany(mappedBy = "campaignList")
    private List<Users> secretaries;

    @OneToMany(mappedBy = "campaign")
    private List<ExpensesBlock> expenses;

    @OneToMany(mappedBy = "campaign")
    private List<TransactionBlock> transactions;
}
