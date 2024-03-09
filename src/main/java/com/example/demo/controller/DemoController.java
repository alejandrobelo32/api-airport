package com.example.demo.controller;

import com.example.demo.entities.Flight;
import com.example.demo.repositories.FlightsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/airport-api")
public class DemoController {

    @Autowired
    FlightsRepository flightsRepository;

    @GetMapping("/flights")
    public ResponseEntity<List<Flight>> getAllFlights() {
        List<Flight> flights = flightsRepository.findAll();
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @GetMapping("/flight/{id}")
    public ResponseEntity<Flight> getById(@PathVariable int id) {
        Flight flight = flightsRepository.findById(id).orElse(null);
        if (flight == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }

    @GetMapping("/flight/{date}/{flightNumber}")
    public ResponseEntity<Flight> getByDepartureDatetimeAndFlightNumber(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") Date date, @PathVariable int flightNumber) {
        Flight flight = flightsRepository.findByDepartureDatetimeAndFlightNumber(date,flightNumber);
        if (flight == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }

    @GetMapping("/airline/{code}/flights")
    public ResponseEntity<List<Flight>> getByAirlineCode(@PathVariable String code) {
        List<Flight> flights = flightsRepository.findByAirlineCode(code);
        if (flights.isEmpty()) {
            return new ResponseEntity<>(flights, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @GetMapping("/flights/{date}")
    public ResponseEntity<List<Flight>> getByDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 1);
        List<Flight> flights = flightsRepository.findByDepartureDatetimeBetween(date, cal.getTime());
        if (flights.isEmpty()) {
            return new ResponseEntity<>(flights, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }
}
