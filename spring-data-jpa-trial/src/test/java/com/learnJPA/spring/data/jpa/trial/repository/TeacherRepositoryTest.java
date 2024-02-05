package com.learnJPA.spring.data.jpa.trial.repository;

import com.learnJPA.spring.data.jpa.trial.entity.Course;
import com.learnJPA.spring.data.jpa.trial.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void saveTeacher(){
        Course courseDBA =
                Course.builder()
                        .title("DBA")
                        .credits(4)
                        .build();

        Course courseJava =
                Course.builder()
                        .title("Java")
                        .credits(4)
                        .build();
        Teacher teacher =
                Teacher.builder()
                        .firstName("Shree")
                        .lastName("Ram")
                        //.courses(List.of(courseDBA , courseJava))
                        .build();

        teacherRepository.save(teacher);

        System.out.println("Teacher : " + teacher);
    }

}