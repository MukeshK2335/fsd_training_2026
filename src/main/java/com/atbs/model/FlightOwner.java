package com.atbs.model;


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
public class FlightOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(nullable = false,unique = true)
    private String companyName;
    @Column(nullable = false)
    private String contactNumber;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false,unique = true)
    private String contactEmail;
    @OneToOne
    private User user;

}
