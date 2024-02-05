package com.learnJPA.spring.data.jpa.trial.repository;

import com.learnJPA.spring.data.jpa.trial.entity.Course;
import com.learnJPA.spring.data.jpa.trial.entity.Student;
import com.learnJPA.spring.data.jpa.trial.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;


@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void printCourses(){
        List<Course> courses =
                courseRepository.findAll();

        System.out.println("Courses : " + courses);
    }

    @Test
    public void saveCoursesWithTeacher(){
        Teacher teacher =
                Teacher.builder()
                        .firstName("Shree")
                        .lastName("Krishna")
                        .build();

        Course course =
                Course.builder()
                        .title("Advanced DSA")
                        .credits(6)
                        .teacher(teacher)
                        .build();

        courseRepository.save(course);
    }

    @Test
    public void findAllPagination(){
        Pageable firstPageWithThreeRecords =
                PageRequest.of(0 , 3);

        Pageable secondPageWithTwoRecords =
                PageRequest.of(1 , 2);

        List<Course> courses = courseRepository.findAll(secondPageWithTwoRecords).getContent();

        long totalElements = courseRepository.findAll(secondPageWithTwoRecords).getTotalElements();
        long totalPages = courseRepository.findAll(secondPageWithTwoRecords).getTotalPages();

        System.out.println("COURSES : " + courses);
        System.out.println("TOTAL ELEMENTS : " + totalElements);
        System.out.println("TOTAL PAGES : " + totalPages);
    }

    @Test
    public void findAllSorting(){
        Pageable sortByTitle =
                PageRequest.of(0 , 2 , Sort.by("title"));
        Pageable sortByCreditDesc =
                PageRequest.of(0 , 2 , Sort.by("credits").descending());
        Pageable sortByTitleAndCreditDesc =
                PageRequest.of(0 , 2 , Sort.by("title").descending().and(Sort.by("credits")));

        List<Course> courses =
                courseRepository.findAll(sortByTitleAndCreditDesc).getContent();
        System.out.println("COURSES : " + courses);
    }

    @Test
    public void printFindByTitleContaining(){
        Pageable firstPageTenRecords =
                PageRequest.of(0 , 10);

        List<Course> courses =
                courseRepository.findByTitleContaining("D" , firstPageTenRecords).getContent();

        System.out.println("COURSES : " + courses);
    }

    @Test
    public void saveCourseWithStudentAndTeacher(){
        Teacher teacher =
                Teacher.builder()
                        .firstName("Mahadev")
                        .lastName("Har Har")
                        .build();
        Student student =
                Student.builder()
                        .firstName("Prajwal")
                        .lastName("Hardekar")
                        .emailId("bow@sanathan.com")
                        .build();
        Course course =
                Course.builder()
                        .title("AI")
                        .credits(6)
                        .teacher(teacher)
                        .build();

        course.addStudents(student);

        courseRepository.save(course);
    }
}