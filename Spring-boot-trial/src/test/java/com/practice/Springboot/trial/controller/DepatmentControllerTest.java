package com.practice.Springboot.trial.controller;

import com.practice.Springboot.trial.entity.Department;
import com.practice.Springboot.trial.exception.DepartmentNotFoundException;
import com.practice.Springboot.trial.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(DepatmentController.class )
class DepatmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    private Department department;
    @BeforeEach
    void setUp() {
        department = Department.builder()
                .departmentCode("IT-02")
                .departmentAddress("Bangalore")
                .departmentName("IT")
                .departmentId(1L)
                .build();
    }

    @Test
    void saveDepartment() throws Exception {
        Department inputDepartment = Department.builder()
                .departmentCode("IT-02")
                .departmentAddress("Bangalore")
                .departmentName("IT")
                .build();

        Mockito.when(departmentService.saveDepartment(inputDepartment))
                .thenReturn(department);

        mockMvc.perform(MockMvcRequestBuilders.post("/departments ")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                "\t\"departmentName\":\"IT\",\n" +
                "\t\"departmentAddress\":\"Bangalore\",\n" +
                "\t\"departmentCode\":\"IT-02\"\n" +
                "}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void fetchDepartmentById() throws Exception {
        Mockito.when(departmentService.fetchDepartmentById(1L))
                .thenReturn(Optional.ofNullable(department));

        mockMvc.perform(MockMvcRequestBuilders.get("/departments/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.departmentName")
                        .value(department.getDepartmentName()));

    }
}