package inf.unideb.hu.exceptions;

public class UnknownInventoryException extends Exception {

    public UnknownInventoryException(){

    }

    public UnknownInventoryException(String message){
        super(message);
    }
}
