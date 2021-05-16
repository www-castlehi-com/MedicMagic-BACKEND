package MedicMagic.user;

public class DifferentPasswordException extends RuntimeException{
    public DifferentPasswordException(String cause) { super(cause); }
}
