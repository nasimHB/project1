package dev.nasim.exception;

public class ManagerNotFoundException extends RuntimeException{
    public ManagerNotFoundException(int id){
        super("Manager " + id + " not found");
    }
}
