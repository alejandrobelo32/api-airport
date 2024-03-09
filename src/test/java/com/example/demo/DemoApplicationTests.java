package com.example.demo;

import com.example.demo.controller.DemoController;
import com.example.demo.entities.Flight;
import com.example.demo.repositories.FlightsRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(DemoController.class)
public class DemoApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FlightsRepository flightsRepository;

	@Test
	public void testGetFlightById() throws Exception {
		int flightId = 123;
		int flightNumber = 321;
		Flight flight = new Flight();
		flight.setId(flightId);
		flight.setFlightNumber(flightNumber);

		given(flightsRepository.findById(flightId)).willReturn(Optional.of(flight));

		mockMvc.perform(get("/airport-api/flight/{id}", flightId))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.flightNumber", is(flightNumber)));

	}
}