package inf.unideb.hu.exceptions;

public class StaffAlreadyExistsException extends Exception{
    public StaffAlreadyExistsException(){

    }

    public StaffAlreadyExistsException(String message){
        super(message);
    }
}
