package inf.unideb.hu.exceptions;

public class UnknownAddressException extends Exception {

    public UnknownAddressException(){

    }

    public UnknownAddressException(String message){
        super(message);
    }
}
