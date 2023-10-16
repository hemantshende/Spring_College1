package com.example.Spring_College.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.Spring_College.entities.Academic;
import com.example.Spring_College.entities.Interests;
import com.example.Spring_College.entities.Qualification;
import com.example.Spring_College.entities.User;
import com.example.Spring_College.repository.AcademicRepository;
import com.example.Spring_College.repository.InterestRepository;
import com.example.Spring_College.repository.QualificationRepository;
import com.example.Spring_College.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AcademicServiceImpl implements AcademicService {
	
	@Autowired
	private AcademicRepository academicRepository;
	@Autowired
	private QualificationRepository qualificationRepository;
	@Autowired
	private InterestRepository interestsRepository;
	@Autowired
	private UserRepository userRepository;
	
	
	public Academic createAcademic(int qualificationId, int interestsId, String previousCollegeName, String specialization, List<String> languagesKnown, List<byte[]> fileData, int userId) {
	    Qualification qualification = qualificationRepository.findById(qualificationId)
	            .orElseThrow(() -> new EntityNotFoundException("Qualification not found"));
	    Interests interests = interestsRepository.findById(interestsId)
	            .orElseThrow(() -> new EntityNotFoundException("Interests not found"));
	    User user = userRepository.findById(userId)
	            .orElseThrow(() -> new EntityNotFoundException("User not found"));

	    Academic academic = new Academic();
	    academic.setQualification(qualification);
	    academic.setInterests(interests);
	    academic.setPreviousCollegeName(previousCollegeName);
	    academic.setSpecialization(specialization);
	    academic.setLaguagesKnown(languagesKnown);
	    academic.setFiles(fileData); // Set the binary file data

	    academic.setUser(user);

	    return academicRepository.save(academic);
	}



	@Override
	public List<Academic> getAllAcademics() {
		
		return academicRepository.findAll();
	}

	@Override
	public Academic getAacademicById(int id) {
		
		return academicRepository.findById(id).orElse(null);
	}

	@Override
	public Academic updateAcademic(int id, Academic academic) {
		if(academicRepository.existsById(id)) {
			return academicRepository.save(academic);
		}else {
			return null;
		}	
	}

	@Override
	public boolean deleteAcademic(int id) {
		if(academicRepository.existsById(id)) {
			
			academicRepository.deleteById(id);
			return true;		
		}
		return false;
	}

	@Override
	public Academic createAcademic(Academic academic) {
		// TODO Auto-generated method stub
		return null;
	}




}
