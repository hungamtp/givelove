package com.example.GiveLove.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

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

    @ManyToOne
    private Users member;

    @OneToMany(mappedBy= "task")
    private List<SubTask> subTasks ;



    public enum Mission{
        buy , give
    }

}
