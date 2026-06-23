    package com.atbs.model;

    import com.atbs.enums.BookingStatus;
    import com.atbs.enums.SeatClass;
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
    public class BookingPassengerSeat {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @ManyToOne
        private Booking booking;
        @ManyToOne
        private Passenger passenger;
        @ManyToOne
        private Seat seat;
        @Column(nullable = false)
        private String passengerName;
        @Column(nullable = false)
        private int age;
        @Column(nullable = false)
        private String seatNumber;
        @Enumerated(EnumType.STRING)
        private SeatClass seatClass;
        @Enumerated(EnumType.STRING)
        private BookingStatus bookingStatus;
    }
