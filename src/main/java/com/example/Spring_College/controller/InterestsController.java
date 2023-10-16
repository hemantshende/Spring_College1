package com.example.Spring_College.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Spring_College.entities.Interests;
import com.example.Spring_College.services.InterestsService;

@RestController
@RequestMapping("/api/v1/interest")
public class InterestsController {

	@Autowired
	private InterestsService interestService;
	
	//Get All Interesrts
	@GetMapping("/getAll")
	public ResponseEntity<List<Interests>> getAllInterests(){
		List<Interests> interests=interestService.getAllInterests();
		if(interests==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(interests,HttpStatus.OK);	
	}
	
	//Get Interest By Id
	@GetMapping("getbyid/{id}")
	public ResponseEntity<Interests> getInterestById (@PathVariable Integer id){
		try {
			Interests interest=interestService.getInterest(id);
			if(interest==null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(interest,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	//Create New Interest
	@PostMapping("/create")
	public ResponseEntity<Interests> createInterests(@RequestBody Interests interests){
		try {
			Interests createdInterest=interestService.createInterest(interests);
			return new ResponseEntity<>(createdInterest,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	//Update Interest
	@PutMapping("update/{id}")
	public ResponseEntity<Interests> updateInterests(@PathVariable Integer id , @RequestBody Interests interests){
		try {
			Interests updateInterest=interestService.updateInterest(id,interests);
			if(updateInterest==null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(updateInterest,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
    // Delete an interest by ID
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteInterest(@PathVariable Integer id) {
        try {
            boolean deleted = interestService.deleteInterest(id);
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
