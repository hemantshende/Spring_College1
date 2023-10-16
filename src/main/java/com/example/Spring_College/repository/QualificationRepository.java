package com.example.Spring_College.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Spring_College.entities.Qualification;

@Repository
public interface QualificationRepository extends JpaRepository<Qualification, Integer>{

}
