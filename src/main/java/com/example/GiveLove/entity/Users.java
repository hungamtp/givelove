package com.example.GiveLove.entity;

import java.time.LocalDate;
import java.util.List;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Users {

    @Id
    @GeneratedValue
    private Long id;

    private String avatar;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String fullName;
    private String address;
    private LocalDate dateOfBirth;
    private Gender gender;


    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;

    @ManyToMany
    private List<Campaign> campaigns;

    @ManyToMany
    private List<Campaign> campaign;

    @OneToMany(mappedBy = "member")
    private List<Task> tasks;

    @OneToMany(mappedBy = "secretaries")
    private List<Campaign> campaignList;

    public enum Gender{
        male , female
    }

}
