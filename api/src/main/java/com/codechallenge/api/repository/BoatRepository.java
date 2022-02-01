package com.codechallenge.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codechallenge.api.model.Boat;

public interface BoatRepository extends JpaRepository<Boat, Long>{
	
	  List<Boat> findByStatus(String status);


}
