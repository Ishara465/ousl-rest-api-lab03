package lk.ousl.api.lab02;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@SpringBootApplication
@RestController
@RequestMapping("/api/students")
public class StudentApplication {

    private final StudentRepository repository;

    public StudentApplication(StudentRepository repository) {
        this.repository = repository;
    }

    public static void main(String[] args) {
        SpringApplication.run(StudentApplication.class, args);
    }

    // GET: Retrieve all students
    @GetMapping
    public List<Student> getStudents() {
        return repository.findAll();
    }

    // POST: Add a new student
    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return repository.save(student);
    }

    // Student Entity
    @Entity
    @Table(name = "students")
    public static class Student {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;
        private String course;

        public Student() {
        }

        public Student(String name, String course) {
            this.name = name;
            this.course = course;
        }

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCourse() {
            return course;
        }

        public void setCourse(String course) {
            this.course = course;
        }
    }

    // Student Repository
    public interface StudentRepository extends JpaRepository<Student, Long> {

    }
}