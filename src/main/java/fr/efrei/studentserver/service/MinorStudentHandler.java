package fr.efrei.studentserver.service;

import org.springframework.stereotype.Component;
import fr.efrei.studentserver.domain.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class MinorStudentHandler {

    private static final Logger logger = LoggerFactory.getLogger(MinorStudentHandler.class);

    public void handleStudents(Student student) {
        if (student.getAge() < 18) {
            // Traitez l'étudiant du groupe d'âge 8 ans ici
            logger.info("Handling Student < 18 yo : {}", student.getName());
            // Faites ce que vous devez faire avec cet étudiant
        }
    }
}
