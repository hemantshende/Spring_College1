package com.example.Spring_College.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.example.Spring_College.entities.Academic;
import com.example.Spring_College.services.AcademicService;
import com.example.Spring_College.services.FileStorageService;

import jakarta.persistence.EntityNotFoundException;


@RestController
@RequestMapping("/api/v1/academic")
public class AcademicController {
	
	@Autowired
	private AcademicService academicService;
	@Autowired
	private FileStorageService fileStorageService;
	
	@PostMapping("/create")
	public ResponseEntity<?> createAcademic(@RequestParam("qualificationId") int qualificationId,
	                                        @RequestParam("interestsId") int interestsId,
	                                        @RequestParam("previousCollegeName") String previousCollegeName,
	                                        @RequestParam("specialization") String specialization,
	                                        @RequestParam("languagesKnown") List<String> languagesKnown,
	                                        @RequestParam("files") List<MultipartFile> files,
	                                        @RequestParam("userId") int userId) throws IOException {
	    List<byte[]> fileDataList = new ArrayList<>();
	    for (MultipartFile file : files) {
	        byte[] fileData = fileStorageService.getFileData(file);
	        fileDataList.add(fileData);
	    }
	    
	    try {
	        Academic academic = academicService.createAcademic(qualificationId, interestsId, previousCollegeName, specialization, languagesKnown, fileDataList, userId);
	        // Return a success response with the created academic object
	        return new ResponseEntity<>("Academic Created Successfully...!!!", HttpStatus.CREATED);
	    } catch (EntityNotFoundException e) {
	        // Handle the case where entities (qualification, interests, user) are not found
	        return new ResponseEntity<>("Entity not found", HttpStatus.NOT_FOUND);
	    } catch (Exception e) {
	        // Handle other exceptions
	        return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}


	//to Get All Academics
	@GetMapping("/getAll")
	public ResponseEntity<List<Academic>> getAllAcademics(){
		
		try {
			List<Academic> academicList=academicService.getAllAcademics();		
			return new ResponseEntity<>(academicList,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	//to Get By Id
	@GetMapping("/getbyid/{id}")
	public ResponseEntity<Academic> getById(@PathVariable int id){
		
		try {
			Academic academic=academicService.getAacademicById(id);
			if(academic!=null) {
				return new ResponseEntity<>(academic,HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
			
	}
	
	//Update academic
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateAcademic(@PathVariable int id, @RequestBody Academic academic){
		
		try {
			Academic updateAcademic=academicService.updateAcademic(id,academic);
			
			if(academic!=null) {
				return new ResponseEntity<>(updateAcademic,HttpStatus.OK);
			}else {
				return new ResponseEntity<>("Please enter valid id",HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	//Delete Academic
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteAcademic(@PathVariable int id){
		
		try {
			boolean deletedAcademic=academicService.deleteAcademic(id);
			if(deletedAcademic==true) {
				return new ResponseEntity<>("deleted Successfully...!!",HttpStatus.OK);
			}else {
				return new ResponseEntity<>("Please enter valid id",HttpStatus.OK);
			}
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
