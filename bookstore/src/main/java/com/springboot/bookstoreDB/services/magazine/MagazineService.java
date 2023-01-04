package com.springboot.bookstoreDB.services.magazine;

import java.util.List;

import com.springboot.bookstoreDB.dto.LibraryItemDTO;
import com.springboot.bookstoreDB.dto.MagazineDTO;
import com.springboot.bookstoreDB.exceptions.ItemNotFoundException;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

public interface MagazineService
{
    ResponseEntity<LibraryItemDTO> addMagazine(final MagazineDTO letterDTO);

    List<EntityModel<LibraryItemDTO>> getMagazineShelf();

    ResponseEntity<LibraryItemDTO> updateMagazine(final Long id, final MagazineDTO newItem) throws ItemNotFoundException;

    ResponseEntity<LibraryItemDTO> removeItem(final Long id);

    ResponseEntity<LibraryItemDTO> getLibraryItem(final Long id);
}
