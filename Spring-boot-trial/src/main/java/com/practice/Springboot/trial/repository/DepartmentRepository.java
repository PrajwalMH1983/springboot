package com.practice.Springboot.trial.repository;

import com.practice.Springboot.trial.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department , Long> {
    Department findBydepartmentNameIgnoreCase(String departmentName);
}
