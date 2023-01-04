package com.springboot.bookstoreDB.controllers;

import java.util.Optional;

import com.springboot.bookstoreDB.dto.LibraryItemDTO;
import com.springboot.bookstoreDB.services.ItemServiceImp;
import com.springboot.bookstoreDB.utils.ItemTypes;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/item")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LibraryItemController
{
    private final ItemServiceImp itemService;

    @PostMapping("/")
    ResponseEntity<LibraryItemDTO> addItem(@RequestBody LibraryItemDTO libraryItem)
    {
        return itemService.addLibraryItem(libraryItem, ItemTypes.getItemType(libraryItem));
    }

    @GetMapping("/")
    public CollectionModel<EntityModel<LibraryItemDTO>> getAllShelves()
    {
        return itemService.getAllShelves();
    }

    @GetMapping("/{itemType}/{id}")
    public ResponseEntity<LibraryItemDTO> getItem(@PathVariable Long id, @PathVariable final Optional<ItemTypes> itemType)
    {
        return itemService.getLibraryItem(id, itemType.orElseThrow());
    }

    @GetMapping("/shelf/{itemType}")
    @LazyCollection(LazyCollectionOption.TRUE)
    public CollectionModel<EntityModel<LibraryItemDTO>> getShelf(@PathVariable final Optional<ItemTypes> itemType)
    {
        return itemService.getShelf(itemType.orElseThrow());
    }

    @PutMapping("/{itemType}/{itemId}")
    ResponseEntity<LibraryItemDTO> updateItem(@RequestBody LibraryItemDTO newItem, @PathVariable Long itemId,
        @PathVariable final Optional<ItemTypes> itemType)
    {

        return itemService.updateItem(itemId, newItem, itemType.orElseThrow());
    }

    @DeleteMapping("/{itemType}/{itemId}")
    ResponseEntity<LibraryItemDTO> deleteItem(@PathVariable Long itemId, @PathVariable final Optional<ItemTypes> itemType)
    {
        return itemService.removeItem(itemId, itemType.orElseThrow());
    }
}
