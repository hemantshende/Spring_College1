package com.example.Spring_College.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Spring_College.entities.Interests;

@Repository
public interface InterestRepository extends JpaRepository<Interests, Integer>{

}
