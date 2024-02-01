package com.practice.Springboot.trial.controller;


import com.practice.Springboot.trial.entity.Department;
import com.practice.Springboot.trial.exception.DepartmentNotFoundException;
import com.practice.Springboot.trial.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DepatmentController {

    @Autowired
    private DepartmentService departmentService;

    //Using slf4j logger implementation iteslf
    private final Logger LOGGER = LoggerFactory.getLogger(DepatmentController.class);
    @PostMapping("/departments")
    public Department saveDepartment(@Validated @RequestBody Department department){
        return departmentService.saveDepartment(department);
    }

    @GetMapping("/departments")
    public List<Department> fetchDepartmentList(){
        return departmentService.fetchDepartmentList();
    }

    @GetMapping("/departments/{id}")
    public Optional<Department> fetchDepartmentById(@PathVariable("id") Long departmentId){
        try {
            return departmentService.fetchDepartmentById(departmentId);
        } catch (DepartmentNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("departments/{id}")
    public String deleteDepartmentById(@PathVariable("id") Long departmentId){
        departmentService.deleteDepartmentById(departmentId);
        return "Department deleted Successfully!!";
    }

    @PutMapping("departments/{id}")
    public Department updateDepartment(@PathVariable("id") Long departmentId , @RequestBody Department department){
        return departmentService.updateDepartment(departmentId , department);
    }

    @GetMapping("/departments/name/{name}")
    public Department fetchDepartmentByName(@PathVariable("name") String departmentName){
        return departmentService.fetchDepartmentByName(departmentName);
    }
}
