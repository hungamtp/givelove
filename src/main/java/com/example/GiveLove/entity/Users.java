package com.example.GiveLove.entity;

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
    private Long money;

    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;

    @ManyToMany
    private List<Campaign> campaigns;

    @OneToMany(mappedBy = "secretaries")
    private List<Campaign> campaignList;

}
