package com.springboot.bookstoreDB.strategies.add;

import com.springboot.bookstoreDB.entity.LibraryItemEntity;

public interface IAddLibraryItem
{
    LibraryItemEntity addItem(final LibraryItemEntity libraryItemEntity);
}
