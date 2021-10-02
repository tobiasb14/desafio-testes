package com.devsuperior.bds02.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.services.CityService;

@RestController
@RequestMapping("/cities")
public class CityController {
	
	@Autowired
	private CityService cityService;
	
	@GetMapping
	public ResponseEntity<List<CityDTO>> findAllSortedByName() {
		return ResponseEntity.ok(cityService.findAllSortedByName());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		cityService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
