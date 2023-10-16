package com.example.Spring_College.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Spring_College.entities.Interests;

public interface InterestsService {

	List<Interests> getAllInterests();

	Interests getInterest(Integer id);

	Interests createInterest(Interests interests);

	Interests updateInterest(Integer id, Interests interests);

	boolean deleteInterest(Integer id);

}
