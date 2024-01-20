package fr.efrei.studentserver.repository;

import fr.efrei.studentserver.domain.ActionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionLogRepository extends JpaRepository<ActionLog, Long> { }
