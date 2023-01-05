package com.springboot.bookstoreDB.exceptions;

public class InvalidQuantityException extends RuntimeException
{
    public InvalidQuantityException()
    {
        super("Quantity must be greater than 0");
    }
}
