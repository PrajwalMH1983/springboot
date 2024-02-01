package com.practice.Springboot.trial.service;

import com.practice.Springboot.trial.entity.Department;
import com.practice.Springboot.trial.exception.DepartmentNotFoundException;
import com.practice.Springboot.trial.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentRepository departmentRepository;
    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> fetchDepartmentList() {
        return departmentRepository.findAll();
    }

    @Override
    public Optional<Department> fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException {
        Optional<Department> department = departmentRepository.findById(departmentId);
        if(!department.isPresent()){
            throw new DepartmentNotFoundException("Department Not Available");
        }

        return department;
    }

    @Override
    public void deleteDepartmentById(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }

    @Override
    public Department updateDepartment(Long departmentId, Department department) {
        Department depDB = departmentRepository.findById(departmentId).get();
        if(Objects.nonNull(department.getDepartmentName()) && !department.getDepartmentName().isBlank()){
            depDB.setDepartmentName(department.getDepartmentName() );
        }
        if(Objects.nonNull(department.getDepartmentCode()) && !department.getDepartmentCode().isBlank()){
            depDB.setDepartmentCode(department.getDepartmentCode() );
        }
        if(Objects.nonNull(department.getDepartmentAddress()) && !department.getDepartmentAddress().isBlank()){
            depDB.setDepartmentAddress(department.getDepartmentAddress() );
        }
        return departmentRepository.save(depDB);
    }

    @Override
    public Department fetchDepartmentByName(String departmentName) {
        return departmentRepository.findBydepartmentNameIgnoreCase(departmentName);
    }
}
