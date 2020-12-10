package inf.unideb.hu.exceptions;

public class AddressAlreadyExistsException extends Exception{
    public AddressAlreadyExistsException(){

    }

    public AddressAlreadyExistsException(String message){
        super(message);
    }
}
