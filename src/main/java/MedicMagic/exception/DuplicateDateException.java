package MedicMagic.exception;

public class DuplicateDateException extends RuntimeException{
    public DuplicateDateException(Throwable cause) { super(cause); }

    public DuplicateDateException(String message, Throwable cause) {
        super(message, cause);
    }
}
