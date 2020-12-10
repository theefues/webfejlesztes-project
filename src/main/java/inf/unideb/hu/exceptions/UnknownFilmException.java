package inf.unideb.hu.exceptions;

public class UnknownFilmException extends Exception {

    public UnknownFilmException(){

    }

    public UnknownFilmException(String message){
        super(message);
    }
}
