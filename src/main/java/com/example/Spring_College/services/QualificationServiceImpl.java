package com.example.Spring_College.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Spring_College.entities.Qualification;
import com.example.Spring_College.repository.QualificationRepository;


@Service
public class QualificationServiceImpl implements QualificationService {

	@Autowired
	private QualificationRepository qualificationRepository;
	
	@Override
	public List<Qualification> getAllQualifications() {
		
		return qualificationRepository.findAll();
	}

	@Override
	public Qualification getQualificationById(Integer id) {
		
		return qualificationRepository.findById(id).orElse(null);
	}

	@Override
	public Qualification createQualification(Qualification qualification) {
		
		return qualificationRepository.save(qualification);
	}

	@Override
	public Qualification updateQualification(Integer id, Qualification qualification) {
	 
		if(qualificationRepository.existsById(id)) {
			
			qualification.setId(id);
			return qualificationRepository.save(qualification);
		}else {
			return null;
		}		
	}

	@Override
	public boolean deleteQualification(Integer id) {
		if(qualificationRepository.existsById(id)) {
			
		qualificationRepository.deleteById(id);
		return true;
		
		}
		return false;
	}
}
