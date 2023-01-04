package com.springboot.bookstoreDB.services.letter;

import java.util.List;

import com.springboot.bookstoreDB.dto.LetterDTO;
import com.springboot.bookstoreDB.dto.LibraryItemDTO;
import com.springboot.bookstoreDB.exceptions.ItemNotFoundException;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

public interface LetterService
{
    ResponseEntity<LibraryItemDTO> addLetter(final LetterDTO letterDTO);

    List<EntityModel<LibraryItemDTO>> getLetterShelf();

    ResponseEntity<LibraryItemDTO> updateLetter(final Long id, final LetterDTO newItem) throws ItemNotFoundException;

    ResponseEntity<LibraryItemDTO> removeItem(final Long id);

    ResponseEntity<LibraryItemDTO> getLibraryItem(final Long id);
}
