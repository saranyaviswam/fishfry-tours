package com.codechallenge.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.codechallenge.api.model.Boat;
import com.codechallenge.api.model.BoatVO;

@SpringBootTest(classes = ApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApiApplicationTests {
	
	private static final String DOCKED= "Docked";
	private static final String OUTBOUND= "Outbound to Sea";
	private static final String INBOUND= "Inbound to Harbor";
	private static final String MAINTENANCE= "Maintenance";

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port + "/api/v1";
	}
	
	@Test
	void contextLoads() {
		Assertions.assertNotNull(restTemplate);
	}
	
	//Create boats and test get all the boats
	@Test
	void testGetAllBoats() {
		
		//create boats
					
		BoatVO boatCreated = createTestBoat(1, "Test1", DOCKED);		
		ResponseEntity<Boat> postResponse = restTemplate.postForEntity(getRootUrl() + "/boats",boatCreated, Boat.class);
		Assertions.assertNotNull(postResponse);
		Assertions.assertNotNull(postResponse.getBody());
		
		BoatVO boatCreated2 = createTestBoat(2, "Test2", OUTBOUND);		
		ResponseEntity<Boat> postResponse2 = restTemplate.postForEntity(getRootUrl() + "/boats",boatCreated2, Boat.class);
		Assertions.assertNotNull(postResponse2);
		Assertions.assertNotNull(postResponse2.getBody());

		//get boats
		
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/boats",
				HttpMethod.GET, entity, String.class);

		Assertions.assertNotNull(response.getBody());
		Assertions.assertEquals(200,response.getStatusCodeValue());
	}

	//Create boats 
	@Test
	void testGetBoatById() {
		Boat boat = restTemplate.getForObject(getRootUrl() + "/boats/1", Boat.class);
		
		Assertions.assertNotNull(boat);
	}

	@Test
	void testCreateBoat() {
		BoatVO boat = createTestBoat(1, "Test1", "Docked");		
		ResponseEntity<Boat> postResponse = restTemplate.postForEntity(getRootUrl() + "/boats", boat, Boat.class);
		Assertions.assertNotNull(postResponse);
		Assertions.assertNotNull(postResponse.getBody());
		Assertions.assertEquals(200,postResponse.getStatusCodeValue());
	}

	@Test
	void testUpdatePost() {
			
		//create boat
		
		BoatVO boatCreated = createTestBoat(1, "Test1", "Docked");		
		ResponseEntity<Boat> postResponse = restTemplate.postForEntity(getRootUrl() + "/boats",boatCreated, Boat.class);
		Assertions.assertNotNull(postResponse);
		Assertions.assertNotNull(postResponse.getBody());
		
		//get boat		
		Boat boatRetrieved = restTemplate.getForObject(getRootUrl() + "/boats/1", Boat.class);
		//update boat

		boatRetrieved.setStatus("Not Docked");
		restTemplate.put(getRootUrl() + "/boats/" + 1, boatRetrieved);

		Boat updatedBoat = restTemplate.getForObject(getRootUrl() + "/boats/" + 1, Boat.class);
		Assertions.assertNotNull(updatedBoat);
	}

	@Test
	void testDeletePost() {
		//create boat
		long id = 1;
		BoatVO boatCreated = createTestBoat(id, "Test1", "Docked");		
		ResponseEntity<Boat> postResponse = restTemplate.postForEntity(getRootUrl() + "/boats",boatCreated, Boat.class);
		Assertions.assertNotNull(postResponse);
		Assertions.assertNotNull(postResponse.getBody());
		
		//get boat		
		Boat boatRetrieved = restTemplate.getForObject(getRootUrl() + "/boats/"+ id, Boat.class);
		Assertions.assertNotNull(boatRetrieved);

		restTemplate.delete(getRootUrl() + "/boats/" + id);
		
	}
	
	
	//get the list of boats with a specified status
	@Test
	void testGetBoatsByStatus() {
		
		//create boats
					
		BoatVO boatCreated = createTestBoat(1, "Test1", INBOUND);		
		ResponseEntity<Boat> postResponse = restTemplate.postForEntity(getRootUrl() + "/boats",boatCreated, Boat.class);
		Assertions.assertNotNull(postResponse);
		Assertions.assertNotNull(postResponse.getBody());
		
		BoatVO boatCreated2 = createTestBoat(2, "Test2", MAINTENANCE);		
		ResponseEntity<Boat> postResponse2 = restTemplate.postForEntity(getRootUrl() + "/boats",boatCreated2, Boat.class);
		Assertions.assertNotNull(postResponse2);
		Assertions.assertNotNull(postResponse2.getBody());

		//get boats
		
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/boats"+"?status="+MAINTENANCE,
				HttpMethod.GET, entity, String.class);

		Assertions.assertNotNull(response.getBody());
		Assertions.assertEquals(200,response.getStatusCodeValue());
	}
	
	@Test
	void testGetBoatByIdNotFound() {
		Boat boat = restTemplate.getForObject(getRootUrl() + "/boats/100", Boat.class);
		
		Assertions.assertNotNull(boat);
	}


	
	private BoatVO createTestBoat(long id, String name, String status) {
		BoatVO boat = new BoatVO();
		boat.setId(id);
		boat.setName(name);
		boat.setStatus(status);
		return boat;
	}

}
