package com.learnJPA.spring.data.jpa.trial.repository;

import com.learnJPA.spring.data.jpa.trial.entity.CourseMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseMaterialRepository extends JpaRepository<CourseMaterial , Long> {
}
