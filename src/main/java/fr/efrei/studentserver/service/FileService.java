package fr.efrei.studentserver.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fr.efrei.studentserver.domain.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class FileService {
    private static final Logger logger = LoggerFactory.getLogger(FileService.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ReentrantLock lock = new ReentrantLock();

    @Value("${data.in.directory:DataIn}")
    private String dataInDir;

    @Value("${data.in.directory:DataOut}")
    private  String dataOutDir;

    public FileService(@Value("${data.in.directory:DataIn}") String dataInDir,@Value("${data.in.directory:DataOut}") String dataOutDir) {
        this.dataInDir = dataInDir;
        this.dataOutDir = dataOutDir;
    }

    public void createOrUpdateStudentJson(Student student) throws IOException {
        String jsonFileName = student.getId() + ".json";
        File file = new File(dataInDir, jsonFileName);
        objectMapper.writeValue(file, student);
    }

    public void logActionToJsonFile(String action, String details) {
        lock.lock();
        String jsonFileName = "logs.json";
        try {
            File file = new File(dataOutDir,jsonFileName); // Utilisation du répertoire de sortie des données
            ArrayNode logsArray;
            if (file.exists()) {
                logsArray = (ArrayNode) objectMapper.readTree(file);
            } else {
                logsArray = objectMapper.createArrayNode();
            }

            ObjectNode logEntry = objectMapper.createObjectNode();
            logEntry.put("timestamp", LocalDateTime.now().toString());
            logEntry.put("action", action);
            logEntry.put("details", details);
            logsArray.add(logEntry);

            objectMapper.writeValue(file, logsArray);
        } catch (IOException e) {
            logger.error("Error writing to log file", e);
        } finally {
            lock.unlock();
        }
    }
}
