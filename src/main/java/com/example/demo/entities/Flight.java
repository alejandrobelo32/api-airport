package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private int id;

    @Column(name = "flight_number", unique = true)
    private int flightNumber;

    @Column(name = "departure_datetime")
    private Date departureDatetime;

    @Column(name = "gate")
    private int gate;

    @ManyToOne
    @JoinColumn(name = "airline")
    private Airline airline;

    @ManyToOne
    @JoinColumn(name = "from_airport")
    private Airport fromAirport;

    @ManyToOne
    @JoinColumn(name = "to_airport")
    private Airport toAirport;

}
