package com.practice.Springboot.trial.service;

import com.practice.Springboot.trial.entity.Department;
import com.practice.Springboot.trial.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {
        Department department = Department.builder()
                .departmentName("IT")
                .departmentAddress("Bangalore")
                .departmentCode("IT-02")
                .departmentId(2L)
                .build();

        Mockito.when(departmentRepository.findBydepartmentNameIgnoreCase("IT"))
                .thenReturn(department);
    }

    @Test
    @DisplayName("Get Data based on Valid Department Name")
    public void whenValidDepartmentName_thenDepartmentShouldBeFound(){
        String departmentName = "IT";
        Department found = departmentService.fetchDepartmentByName(departmentName);

        assertArrayEquals(departmentName.toCharArray(), found.getDepartmentName().toCharArray());
    }
}