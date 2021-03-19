package dev.nasim.exception;

public class InsufficientPrivilegesException extends RuntimeException{
    public InsufficientPrivilegesException(){
        super("Insufficient privileges to perform this action");
    }
}
