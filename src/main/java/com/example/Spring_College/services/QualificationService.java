package com.example.Spring_College.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Spring_College.entities.Qualification;

public interface QualificationService {

	List<Qualification> getAllQualifications();

	Qualification getQualificationById(Integer id);

	Qualification createQualification(Qualification qualification);

	Qualification updateQualification(Integer id, Qualification qualification);

	boolean deleteQualification(Integer id);
}
