package com.springboot.bookstoreDB.strategies.get;

import com.springboot.bookstoreDB.entity.LibraryItemEntity;

public interface IGetLibraryItem
{
    LibraryItemEntity getItem(final Long id);
}
