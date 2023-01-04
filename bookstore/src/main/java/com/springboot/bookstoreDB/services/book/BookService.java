package com.springboot.bookstoreDB.services.book;

import java.util.List;

import com.springboot.bookstoreDB.dto.BookDTO;
import com.springboot.bookstoreDB.dto.LibraryItemDTO;
import com.springboot.bookstoreDB.exceptions.ItemNotFoundException;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

public interface BookService
{
    ResponseEntity<LibraryItemDTO> addBook(final BookDTO bookDTO);

    List<EntityModel<LibraryItemDTO>> getBookShelf();

    ResponseEntity<LibraryItemDTO> updateBook(final Long id, final BookDTO newItem) throws ItemNotFoundException;

    ResponseEntity<LibraryItemDTO> removeItem(final Long id);

    ResponseEntity<LibraryItemDTO> getLibraryItem(final Long id);
}
