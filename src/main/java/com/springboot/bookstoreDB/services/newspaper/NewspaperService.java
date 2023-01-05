package com.springboot.bookstoreDB.services.newspaper;

import java.util.List;

import com.springboot.bookstoreDB.dto.LibraryItemDTO;
import com.springboot.bookstoreDB.dto.NewspaperDTO;
import com.springboot.bookstoreDB.exceptions.ItemNotFoundException;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

public interface NewspaperService
{
    ResponseEntity<LibraryItemDTO> addNewspaper(final NewspaperDTO newspaperDTO);

    List<EntityModel<LibraryItemDTO>> getNewspaperShelf();

    ResponseEntity<LibraryItemDTO> updateNewspaper(final Long id, final NewspaperDTO newItem) throws ItemNotFoundException;

    ResponseEntity<LibraryItemDTO> removeItem(final Long id);

    ResponseEntity<LibraryItemDTO> getLibraryItem(final Long id);
}
