package dev.nasim.exception;

public class BadFormatException extends RuntimeException{
    public BadFormatException(){
        super("Response body contained incorrect format");
    }
}
