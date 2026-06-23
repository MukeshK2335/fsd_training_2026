package com.atbs.model;

import com.atbs.enums.Gender;
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
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false,length = 45)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;
    @Column(name = "contact_number", nullable = false,length = 10)
    private String contactNumber;
    private String address;
    @Column(nullable = false,unique = true)
    private String email;
    @OneToOne
    private User user;

    private String idPath;
}
