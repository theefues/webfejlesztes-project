package inf.unideb.hu.exceptions;

public class UnknownCountryException extends Exception {

    public UnknownCountryException(){

    }

    public UnknownCountryException(String message){
        super(message);
    }
}
