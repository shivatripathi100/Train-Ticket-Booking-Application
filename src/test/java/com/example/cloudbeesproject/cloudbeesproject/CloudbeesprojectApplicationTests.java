package com.example.cloudbeesproject.cloudbeesproject;

import java.util.Map;
import java.util.Objects;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CloudbeesprojectApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private final RestTemplate restTemplate = new RestTemplate();

	@Test
	public void testContextLoads() {
	}

	@Test
	public void testPurchaseTicket() {
		TicketPurchase ticketPurchase = new TicketPurchase("London", "France", "Jane", "Doe", "jane@example.com", 20.0,
				"A1", "A");
		ResponseEntity<Void> responseEntity = restTemplate.postForEntity(getUrl("/purchaseTicket"), ticketPurchase,
				Void.class);
		assertEquals(200, responseEntity.getStatusCodeValue());
	}

	@Test
	public void testGetUsersAndSeatsForSection() {
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(getUrl("/usersAndSeats/A"), String.class);
		assertEquals(200, responseEntity.getStatusCodeValue());
	}

	@SuppressWarnings("null")
	@Test
	public void testGetReceiptDetailsByEmail() {
		TicketPurchase ticketPurchase = new TicketPurchase("London", "Paris", "Jane", "Doe", "jane@example.com", 20.0, "A1",
				"A");
		restTemplate.postForEntity(getUrl("/purchaseTicket"), ticketPurchase, Void.class);

		ResponseEntity<Map<String, Object>> responseEntity = restTemplate.exchange(
				getUrl("/receiptDetails/jane@example.com"),
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<Map<String, Object>>() {
				});

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertNotNull(responseEntity.getBody());
		assertEquals("London", responseEntity.getBody().get("from"));
		assertEquals("Paris", responseEntity.getBody().get("to"));
		assertEquals(20.0, responseEntity.getBody().get("price"));
		assertEquals("A1", responseEntity.getBody().get("seat"));
	}

	@Test
	public void testRemoveUser() {
		ResponseEntity<Void> responseEntity = restTemplate.exchange(getUrl("/removeUser/jane@example.com"),
				HttpMethod.DELETE, null, Void.class);
		assertEquals(200, responseEntity.getStatusCodeValue());
	}

	@Test
	public void testModifySeat() {
		ResponseEntity<String> responseEntity = restTemplate.exchange(getUrl("/modifySeat/jane@example.com"),
				HttpMethod.PUT, null, String.class);
		assertEquals(200, responseEntity.getStatusCodeValue());
	}

	private String getUrl(String path) {
		String nonNullPath = Objects.requireNonNull(path, "Path cannot be null");
		assert port != 0 : "Port is not initialized";
		return "http://localhost:" + port + nonNullPath;
	}
}
