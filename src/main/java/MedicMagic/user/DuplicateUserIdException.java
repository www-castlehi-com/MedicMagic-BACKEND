package MedicMagic.user;

public class DuplicateUserIdException extends RuntimeException{
    public DuplicateUserIdException(Throwable cause) {
        super(cause);
    }

    public DuplicateUserIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateUserIdException(String message) {
        super(message);
    }
}
