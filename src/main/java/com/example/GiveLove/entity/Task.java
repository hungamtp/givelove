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
public class Task {

    @Id
    @GeneratedValue
    private Long id;
    private float quantity;
    private float quantityRemain;
    private String description;
    private String gift;
    private String unit;
    private Mission type;
    private LocalDate createdDate;
    private LocalDate deadline;
    private boolean status;
    @ManyToOne
    private Campaign campaign;

    public enum Mission{
        buy , give
    }

}
