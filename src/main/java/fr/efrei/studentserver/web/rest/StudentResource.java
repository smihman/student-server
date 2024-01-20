package fr.efrei.studentserver.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fr.efrei.studentserver.domain.ActionLog;
import fr.efrei.studentserver.domain.Student;
import fr.efrei.studentserver.service.FileService;
import fr.efrei.studentserver.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import fr.efrei.studentserver.repository.ActionLogRepository;
import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;

@RestController
@RequestMapping("/students")
public class StudentResource {

    private final StudentService studentService;
    private final FileService fileService;


    @Autowired
    public StudentResource(StudentService studentService, ActionLogRepository actionLogRepository, FileService fileService) {
        this.studentService = studentService;
        this.fileService = fileService;
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) throws IOException {
        fileService.logActionToJsonFile("ADD", "Adding new student: " + student.toString());
        return studentService.addStudent(student);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Integer id) {
        return studentService.getStudentById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Integer id, @RequestBody Student studentDetails) {
        try {
            fileService.logActionToJsonFile("CHANGE", "Adding new student: " + id.toString());
            Student updatedStudent = studentService.updateStudent(id, studentDetails);
            return ResponseEntity.ok(updatedStudent);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer id) {
        try {
            fileService.logActionToJsonFile("DELETE", "Adding new student: " + id.toString());
            studentService.deleteStudent(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}