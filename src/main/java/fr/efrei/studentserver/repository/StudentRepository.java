package fr.efrei.studentserver.repository;

import fr.efrei.studentserver.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    // Basic CRUD operations are already included
}
