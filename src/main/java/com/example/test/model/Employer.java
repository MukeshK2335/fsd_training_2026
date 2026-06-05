package com.example.test.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employer {
    @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "company_name")
    private String companyName;

    @OneToOne
    private User user;
}
