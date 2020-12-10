package inf.unideb.hu.exceptions;

public class LanguageAlreadyExistsException extends Exception{
    public LanguageAlreadyExistsException(){

    }

    public LanguageAlreadyExistsException(String message){
        super(message);
    }
}
