package com.example.GiveLove.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubTask {

    @Id
    @GeneratedValue
    private Long id;

    private float quantity;
    private float money;
    private String description;
    private String image;
    private LocalDate createdDate;
    private boolean isApproved;

    @ManyToOne
    private Task task;
}
