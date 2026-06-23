package com.atbs.model;

import com.atbs.enums.PaymentMethod;
import com.atbs.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private Double amount;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethod paymentMethod;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus paymentStatus=PaymentStatus.PENDING;
    @CreationTimestamp
    @Column(nullable = false)
    private Instant paymentTime;
    @Column(nullable = false,unique = true)
    private  String transactionId;
    @OneToOne
    private Booking booking;

}
