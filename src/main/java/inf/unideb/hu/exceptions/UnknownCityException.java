package inf.unideb.hu.exceptions;

public class UnknownCityException extends Exception {

    public UnknownCityException(){

    }

    public UnknownCityException(String message){
        super(message);
    }
}
