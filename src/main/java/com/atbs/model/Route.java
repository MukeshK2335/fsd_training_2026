package com.atbs.model;

import com.atbs.enums.RouteStatus;
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
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false,length = 45)
    private String origin;
    @Column(nullable = false,length = 45)
    private String destination;
    @Enumerated(EnumType.STRING)
    private RouteStatus routeStatus;
}
