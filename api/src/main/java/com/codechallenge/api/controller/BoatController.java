package com.codechallenge.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codechallenge.api.exception.NoDataFoundException;
import com.codechallenge.api.model.Boat;
import com.codechallenge.api.model.BoatVO;
import com.codechallenge.api.repository.BoatRepository;

@RestController
@RequestMapping("/api/v1")
public class BoatController {

	private static final String NO_DATA_FOUND_ERROR = "No boat found with id : ";
	
	@Autowired
	private BoatRepository boatRepository;

	/**
	 * Get all the boats.
	 *
	 * @return the list
	 */
	@GetMapping("/boats")
	public List<Boat> getAllBoats(@RequestParam(required = false) String status) {
		if (status == null) {
			return boatRepository.findAll();
		} else {
			return boatRepository.findByStatus(status);
		}
	}

	/**
	 * Gets boat by id.
	 *
	 * @param boatId the boat id
	 * @return the boats by id
	 * @throws NoDataFoundException
	 */
	@GetMapping("/boats/{id}")
	public ResponseEntity<Boat> getBoatsById(@PathVariable(value = "id") Long boatId) throws NoDataFoundException {
		Boat boat = boatRepository.findById(boatId)
				.orElseThrow(() -> new NoDataFoundException(NO_DATA_FOUND_ERROR + boatId));
		return ResponseEntity.ok().body(boat);
	}

	/**
	 * Create boat.
	 *
	 * @param boat
	 * @return the boat
	 */
	@PostMapping("/boats")
	public Boat createBoat(@RequestBody BoatVO boatVO) {
		Boat boat = new Boat(boatVO);
		return boatRepository.save(boat);
	}

	/**
	 * Update boat response entity.
	 *
	 * @param boatId      the boat id
	 * @param boatToUpdate the boat details
	 * @return the response entity
	 * @throws NoDataFoundException
	 */
	@PutMapping("/boats/{id}")
	public ResponseEntity<Boat> updateBoat(@PathVariable(value = "id") Long boatId, @RequestBody BoatVO boatToUpdate)
			throws NoDataFoundException {

		Boat boat = boatRepository.findById(boatId)
				.orElseThrow(() -> new NoDataFoundException(NO_DATA_FOUND_ERROR  + boatId));

		boat.setStatus(boatToUpdate.getStatus());
		boat.setName(boatToUpdate.getName());
		final Boat boatAfterUpdate = boatRepository.save(boat);
		return ResponseEntity.ok(boatAfterUpdate);
	}
	
	/**
	   * Delete boat.
	   *
	   * @param boatId the boat id
	   * @return the map
	   * @throws NoDataFoundException
	   */
	  @DeleteMapping("/boats/{id}")
	  public Map<String, Boolean> deleteBoats(@PathVariable(value = "id") Long boatId) throws NoDataFoundException {
	    Boat boat =
	    		boatRepository
	            .findById(boatId)
	            .orElseThrow(() -> new NoDataFoundException(NO_DATA_FOUND_ERROR + boatId));
	
	    boatRepository.delete(boat);
	    Map<String, Boolean> response = new HashMap<>();
	    response.put("deleted", Boolean.TRUE);
	    return response;
	  }

}
