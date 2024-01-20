package fr.efrei.studentserver.service;

import fr.efrei.studentserver.domain.Student;
import fr.efrei.studentserver.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final FileService fileService;

    @Autowired
    public StudentService(StudentRepository studentRepository, FileService fileService) {
        this.studentRepository = studentRepository;
        this.fileService = fileService;
    }

    public Student addStudent(Student student) {
        Student savedStudent = studentRepository.save(student);
        try {
            fileService.createOrUpdateStudentJson(savedStudent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return savedStudent;
    }

    public Optional<Student> getStudentById(Integer id) {
        return studentRepository.findById(id);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student updateStudent(Integer id, Student studentDetails) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with ID: " + id));
        student.setName(studentDetails.getName());
        student.setAge(studentDetails.getAge());
        Student updatedStudent = studentRepository.save(student);
        try {
            fileService.createOrUpdateStudentJson(updatedStudent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return updatedStudent;
    }

    public void deleteStudent(Integer id) {
        studentRepository.deleteById(id);
    }

    public void logStudentInfo(Student student){
        System.out.println(student.getName());
    }

}
