package db;

//Tratamento para erro de Integridade Referencial
public class DbIntegrityException extends RuntimeException{
    public DbIntegrityException(String msg){
        super(msg);
    }
}
