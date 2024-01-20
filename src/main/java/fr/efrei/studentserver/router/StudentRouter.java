package fr.efrei.studentserver.router;

import fr.efrei.studentserver.domain.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StudentRouter {

    @Value("${student.age.majority:18}")
    private int majorityAge;

    public String routeStudent(Student student) {
        // Check if the student's age is less than the majority age
        if (student.getAge() < majorityAge) {
            // Route to the channel for minors
            return "studentsMinorChannel";
        } else {
            // Route to the channel for majors
            return "studentsMajorChannel";
        }
    }
}

