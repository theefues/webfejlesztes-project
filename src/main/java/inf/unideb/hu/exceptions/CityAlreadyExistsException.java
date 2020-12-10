package inf.unideb.hu.exceptions;

public class CityAlreadyExistsException extends Exception{
    public CityAlreadyExistsException(){

    }

    public CityAlreadyExistsException(String message){
        super(message);
    }
}
