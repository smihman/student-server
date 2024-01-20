package fr.efrei.studentserver.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;

public class ErrorHandlerService {

    private static final Logger logger = LoggerFactory.getLogger(ErrorHandlerService.class);

    public void handleError(Message<?> message) {
        if (message.getPayload() instanceof MessagingException) {
            MessagingException messagingException = (MessagingException) message.getPayload();
            logger.error("Error processing message: " + messagingException.getMessage(), messagingException);
            // Additional error handling logic here (if necessary)
        } else {
            logger.error("Error processing message: " + message.getPayload());
        }
    }
}
