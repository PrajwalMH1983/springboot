package com.learnJPA.spring.data.jpa.trial.repository;

import com.learnJPA.spring.data.jpa.trial.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course , Long> {
    Page<Course> findByTitleContaining(String title , Pageable pageable);
}
