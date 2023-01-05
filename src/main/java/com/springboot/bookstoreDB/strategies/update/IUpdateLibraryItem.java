package com.springboot.bookstoreDB.strategies.update;

import com.springboot.bookstoreDB.entity.LibraryItemEntity;

public interface IUpdateLibraryItem
{
    LibraryItemEntity updateItem(final LibraryItemEntity oldLibraryItemEntity, final LibraryItemEntity newLibraryItemEntity);
}
