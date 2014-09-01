package ua.vn.os.ulteam.service.exceptions;

/**
 * @author os
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }
}
