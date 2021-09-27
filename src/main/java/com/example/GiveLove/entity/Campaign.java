package com.example.GiveLove.entity;

import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.List;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
//cache
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Campaign {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;
    private String image;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long money;
    private String location;
    private String state;

    @ManyToMany(mappedBy = "campaigns")
    private List<Users> sponsors;

    @ManyToMany(mappedBy = "campaign")
    private List<Users> members;

    @ManyToOne
    private Users secretaries;

    @OneToMany(mappedBy = "campaign")
    private List<ExpensesBlock> expenses;
}
