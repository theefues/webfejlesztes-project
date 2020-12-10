package inf.unideb.hu.exceptions;

public class CountryAlreadyExistsException extends Exception{
    public CountryAlreadyExistsException(){

    }

    public CountryAlreadyExistsException(String message){
        super(message);
    }
}
