package fr.efrei.studentserver.service;

import fr.efrei.studentserver.domain.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MajorStudentHandler {

    private static final Logger logger = LoggerFactory.getLogger(MajorStudentHandler.class);

    public void handleStudents(Student student) {
        if (student.getAge() >= 18) {
            // Traitez l'étudiant du groupe d'âge 10 ans ici
            logger.info("Handling Student > 18 yo : {}", student.getName());
            // Faites ce que vous devez faire avec cet étudiant
        }
    }
}
