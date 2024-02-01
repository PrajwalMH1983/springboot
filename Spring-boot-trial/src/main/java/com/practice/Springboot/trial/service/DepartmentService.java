package com.practice.Springboot.trial.service;

import com.practice.Springboot.trial.entity.Department;
import com.practice.Springboot.trial.exception.DepartmentNotFoundException;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    Department saveDepartment(Department department);

    List<Department> fetchDepartmentList();

    Optional<Department> fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException;

    void deleteDepartmentById(Long departmentId);

    Department updateDepartment(Long departmentId, Department department);

    Department fetchDepartmentByName(String departmentName);
}
