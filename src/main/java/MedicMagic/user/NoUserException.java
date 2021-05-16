package MedicMagic.user;

public class NoUserException extends IllegalArgumentException{
    public NoUserException(String cause) {
        super(cause);
    }
}
