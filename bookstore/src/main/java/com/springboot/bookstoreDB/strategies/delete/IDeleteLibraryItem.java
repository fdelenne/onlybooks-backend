package com.springboot.bookstoreDB.strategies.delete;

import com.springboot.bookstoreDB.entity.LibraryItemEntity;

public interface IDeleteLibraryItem
{
    void deleteItem(final LibraryItemEntity itemToDelete);
}
