package com.atbs.model;

import com.atbs.enums.CancellationStatus;
import com.atbs.enums.RefundStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cancellation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @CreationTimestamp
    @Column(nullable = false,name = "cancelation_date")
    private LocalDateTime cancelationDate;
    @Column(nullable = false,length = 255)
    private String reason;
    @Column(nullable = false,name = "refund_amount")
    private Double refundAmount;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false,name ="refund_status")
    private RefundStatus refundStatus=RefundStatus.NOT_APPLICABLE;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false,name = "cancellation_status")
    private CancellationStatus cancellationStatus=CancellationStatus.REQUESTED;
    @OneToOne
    private Booking booking;

}
