package MedicMagic.sqlService;

public interface SqlService {
    String getSql(String key) throws SqlRetrievalFailureException;
}