package org.example.springboot_project_first.exceptionHandling;

import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
/**
 * A custom runtime exception we throw when something isn't found.
 * Extends RuntimeException so we don't need to declare 'throws' everywhere (unchecked).
 */
public class ResourceNotFoundException extends RuntimeException{

    /**
     * We pass the error message from where we throw it.
     * Example: throw new ResourceNotFoundException("Quiz not found with ID: " + id);
     */

    public ResourceNotFoundException(String message) {
        super(message);// stores the message inside the exception
    }
}
