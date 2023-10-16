package com.example.Spring_College.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Spring_College.entities.Qualification;
import com.example.Spring_College.services.QualificationService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/qualifications")
public class QualificationController {
	
	
	@Autowired
	private QualificationService qualificationService;

	// Get all qualifications
	@GetMapping("/getAll")
	public ResponseEntity<List<Qualification>> getAllQualifications() {
		List<Qualification> qualifications = qualificationService.getAllQualifications();
		return new ResponseEntity<>(qualifications, HttpStatus.OK);
	}

	// Get qualification by ID
	@GetMapping("getbyid/{id}")
	public ResponseEntity<Qualification> getQualificationById(@PathVariable Integer id) {
		try {
			Qualification qualification = qualificationService.getQualificationById(id);
			if (qualification == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} 
			else {
				return new ResponseEntity<>(qualification, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Create a new qualification
	@PostMapping("/create")
	public ResponseEntity<Qualification> createQualification(@RequestBody Qualification qualification) {
		try {
			Qualification createdQualification = qualificationService.createQualification(qualification);
			return new ResponseEntity<>(createdQualification, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Update an existing qualification
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateQualification(@PathVariable Integer id,
			@RequestBody Qualification qualification) {
		try {
			Qualification updatedQualification = qualificationService.updateQualification(id, qualification);
			if (updatedQualification == null) {
				return new ResponseEntity<>("Please Enter Valid id",HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(updatedQualification, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Delete a qualification by ID
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteQualification(@PathVariable Integer id) {
		try {
			boolean deleted = qualificationService.deleteQualification(id);
			if (!deleted) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Exception handler for other exceptions
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception e) {
		return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
