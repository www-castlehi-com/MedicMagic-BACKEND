package MedicMagic.exception;

public class DifferentPasswordException extends RuntimeException{
    public DifferentPasswordException(String cause) { super(cause); }
}
