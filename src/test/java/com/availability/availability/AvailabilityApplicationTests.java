package com.availability.availability;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.availability.availability.controller.AvailabilityController;
import com.availability.availability.model.Availability;
import com.availability.availability.repository.AvailabilityRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureJsonTesters
@AutoConfigureMockMvc
@SpringBootTest
class AvailabilityApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Mock
	private AvailabilityRepository availabilityRepository;

	@InjectMocks
	private AvailabilityController availabilityController;

	private JacksonTester<Availability> jsonAvailability;
	private JacksonTester<List<Availability>> jsonAvailabilities;

	@BeforeEach
	public void setUp() {
		JacksonTester.initFields(this, new ObjectMapper());
		mvc = MockMvcBuilders.standaloneSetup(availabilityController).build();
	}

	// Test1: Adding an availability

	@Test
	public void canAddAnAvailability() throws Exception {
		Availability availability1 = new Availability(1L, Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()),
				"30 minutes");

		when(availabilityRepository.save(availability1)).thenReturn((availability1));

		mvc.perform(MockMvcRequestBuilders
				.post("/availability/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonAvailability.write(availability1).getJson()))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	// Test2:Getting all availabilities
	@Test
	public void canGetAllAvailabilities() throws Exception {
		Availability availability1 = new Availability(1L, Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()),
				"30 minutes");
		Availability availability2 = new Availability(2L, Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()),
				"30 minutes");
		List<Availability> listOfAvailabilities = new ArrayList<>();
		listOfAvailabilities.add(availability1);
		listOfAvailabilities.add(availability2);

		when(availabilityRepository.findAll()).thenReturn(listOfAvailabilities);

		mvc.perform(MockMvcRequestBuilders
				.get("/availability/all")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(
						MockMvcResultMatchers.content().json(jsonAvailabilities.write(listOfAvailabilities).getJson()));

	}

	// Test3: Getting an availability
	@Test
	public void canGetAnAvailability() throws Exception {
		Availability availability1 = new Availability(1L, Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()),
				"30 minutes");

		when(availabilityRepository.findById(1L)).thenReturn(Optional.of(availability1));

		mvc.perform(MockMvcRequestBuilders.get("/availability/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(jsonAvailability.write(availability1).getJson()));
	}

	// Test4: delete all availabilities
	@Test
	public void canDeleteAllAvailabilities() throws Exception {
		doNothing().when(availabilityRepository).deleteAll();

		mvc.perform(MockMvcRequestBuilders
				.delete("/availability/deleteall"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	// Test5: delete an availability with id
	@Test
	public void canDeleteAnAvailability() throws Exception {
		Long id1 = 1L;

		doNothing().when(availabilityRepository).deleteById(id1);

		mvc.perform(MockMvcRequestBuilders
				.delete("/availability/delete/1"))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	// Test6: update an availability
	@Test
	public void canUpdateAnAvailability() throws Exception {
		Availability updateAvailability = new Availability(1L, Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()),
				"30 minutes");

		when(availabilityRepository.save(updateAvailability)).thenReturn((updateAvailability));
		mvc.perform(MockMvcRequestBuilders
				.put("/availability//update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonAvailability.write(updateAvailability).getJson()))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

}
