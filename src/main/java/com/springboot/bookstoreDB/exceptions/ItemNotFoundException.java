package com.springboot.bookstoreDB.exceptions;

public class ItemNotFoundException extends RuntimeException
{
    public ItemNotFoundException(final Long id)
    {
        super("Could not find item with id: " + id);
    }
}
