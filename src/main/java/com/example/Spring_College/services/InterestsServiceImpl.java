package com.example.Spring_College.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Spring_College.entities.Interests;
import com.example.Spring_College.repository.InterestRepository;

@Service
public class InterestsServiceImpl implements InterestsService{

	@Autowired
	private InterestRepository interestsRepository;
	
	@Override      //GET ALL INTERESTS
	public List<Interests> getAllInterests() {
		
		return interestsRepository.findAll() ;
	}

	@Override      //GET BY ID
	public Interests getInterest(Integer id) {
		
		return interestsRepository.findById(id).orElse(null);
	}

	@Override     //CREATE
	public Interests createInterest(Interests interests) {
		
		return interestsRepository.save(interests);
	}

	@Override      //UPDATE
	public Interests updateInterest(Integer id, Interests interests) {
		
		if(interestsRepository.existsById(id)) {
			interests.setId(id);
			return interestsRepository.save(interests);
		}else {
			return null;
		}		
	}

	@Override      //DELETE
	public boolean deleteInterest(Integer id) {
		if(interestsRepository.existsById(id)) {
			interestsRepository.deleteById(id);
			return true;
		}else {
			return false;
		}
	}

}
