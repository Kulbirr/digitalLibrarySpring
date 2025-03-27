package com.example.Digital_Library.Digital_Library.CustomException;

public class BookNotFoundException extends Exception{
    public BookNotFoundException(String message){
        super(message);
    }
}
