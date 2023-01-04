package com.springboot.bookstoreDB.utils;

import com.springboot.bookstoreDB.dto.BookDTO;
import com.springboot.bookstoreDB.dto.LetterDTO;
import com.springboot.bookstoreDB.dto.LibraryItemDTO;
import com.springboot.bookstoreDB.dto.MagazineDTO;
import com.springboot.bookstoreDB.dto.NewspaperDTO;
import com.springboot.bookstoreDB.entity.BookEntity;
import com.springboot.bookstoreDB.entity.LetterEntity;
import com.springboot.bookstoreDB.entity.LibraryItemEntity;
import com.springboot.bookstoreDB.entity.MagazineEntity;
import com.springboot.bookstoreDB.entity.NewspaperEntity;

public enum ItemTypes
{
    BOOK, LETTER, NEWSPAPER, MAGAZINE;

    public static ItemTypes getItemType(LibraryItemDTO libraryItemDTO)
    {
        if (libraryItemDTO instanceof BookDTO)
        {
            return ItemTypes.BOOK;
        }
        else if (libraryItemDTO instanceof LetterDTO)
        {
            return ItemTypes.LETTER;
        }
        else if (libraryItemDTO instanceof MagazineDTO)
        {
            return ItemTypes.MAGAZINE;
        }
        else if (libraryItemDTO instanceof NewspaperDTO)
        {
            return ItemTypes.NEWSPAPER;
        }
        return null;
    }

    public static String getItemEntityType(LibraryItemEntity libraryItem)
    {
        if (libraryItem instanceof BookEntity)
        {
            return "book";
        }
        else if (libraryItem instanceof LetterEntity)
        {
            return "letter";
        }
        else if (libraryItem instanceof MagazineEntity)
        {
            return "magazine";
        }
        else if (libraryItem instanceof NewspaperEntity)
        {
            return "newspaper";
        }
        return null;
    }
}


