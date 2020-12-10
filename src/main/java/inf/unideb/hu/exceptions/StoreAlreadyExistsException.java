package inf.unideb.hu.exceptions;

public class StoreAlreadyExistsException extends Exception{
    public StoreAlreadyExistsException(){

    }

    public StoreAlreadyExistsException(String message){
        super(message);
    }
}
