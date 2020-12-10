package inf.unideb.hu.exceptions;

public class FilmAlreadyExistsException extends Exception{
    public FilmAlreadyExistsException(){

    }

    public FilmAlreadyExistsException(String message){
        super(message);
    }
}
