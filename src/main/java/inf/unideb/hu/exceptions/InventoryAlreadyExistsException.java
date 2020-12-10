package inf.unideb.hu.exceptions;

public class InventoryAlreadyExistsException extends Exception{
    public InventoryAlreadyExistsException(){

    }

    public InventoryAlreadyExistsException(String message){
        super(message);
    }
}
