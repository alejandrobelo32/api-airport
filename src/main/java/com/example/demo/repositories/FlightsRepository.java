package com.example.demo.repositories;

import com.example.demo.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.Date;
import java.util.List;

@RepositoryRestResource
public interface FlightsRepository extends JpaRepository<Flight, Integer> {

    List<Flight> findByAirlineCode(String code);

    List<Flight> findByDepartureDatetimeBetween(Date dateFrom, Date dateTo);

    Flight findByDepartureDatetimeAndFlightNumber(Date date, int flightNumber);

}
