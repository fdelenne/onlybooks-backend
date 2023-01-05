package com.springboot.bookstoreDB.services;

import com.springboot.bookstoreDB.dto.LibraryItemDTO;
import com.springboot.bookstoreDB.exceptions.ItemNotFoundException;
import com.springboot.bookstoreDB.utils.ItemTypes;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

public interface ItemService
{
    ResponseEntity<LibraryItemDTO> addLibraryItem(final LibraryItemDTO item, ItemTypes itemTypes);

    CollectionModel<EntityModel<LibraryItemDTO>> getShelf(ItemTypes shelf);

    CollectionModel<EntityModel<LibraryItemDTO>> getAllShelves();

    ResponseEntity<LibraryItemDTO> updateItem(final Long id, final LibraryItemDTO newItem, final ItemTypes shelf)
        throws ItemNotFoundException;

    ResponseEntity<LibraryItemDTO> removeItem(final Long id, final ItemTypes shelf);

    ResponseEntity<LibraryItemDTO> getLibraryItem(final Long id, final ItemTypes shelf);
}
