package com.springboot.bookstoreDB.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.springboot.bookstoreDB.controllers.LibraryItemController;
import com.springboot.bookstoreDB.dto.BookDTO;
import com.springboot.bookstoreDB.dto.LetterDTO;
import com.springboot.bookstoreDB.dto.LibraryItemDTO;
import com.springboot.bookstoreDB.dto.MagazineDTO;
import com.springboot.bookstoreDB.dto.NewspaperDTO;
import com.springboot.bookstoreDB.exceptions.InvalidQuantityException;
import com.springboot.bookstoreDB.exceptions.ItemNotFoundException;
import com.springboot.bookstoreDB.services.book.BookService;
import com.springboot.bookstoreDB.services.letter.LetterService;
import com.springboot.bookstoreDB.services.magazine.MagazineService;
import com.springboot.bookstoreDB.services.newspaper.NewspaperService;
import com.springboot.bookstoreDB.utils.ItemTypes;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class ItemServiceImp implements ItemService
{
    private final BookService bookService;

    private final LetterService letterService;

    private final MagazineService magazineService;

    private final NewspaperService newspaperService;

    @Override
    public ResponseEntity<LibraryItemDTO> addLibraryItem(final LibraryItemDTO libraryItemDTO, ItemTypes itemType)
    {
        if (libraryItemDTO.getQuantity() < 1)
        {
            throw new InvalidQuantityException();
        }
        switch (itemType)
        {
            case BOOK:
                return bookService.addBook((BookDTO) libraryItemDTO);
            case LETTER:
                return letterService.addLetter((LetterDTO) libraryItemDTO);
            case MAGAZINE:
                return magazineService.addMagazine((MagazineDTO) libraryItemDTO);
            case NEWSPAPER:
                return newspaperService.addNewspaper((NewspaperDTO) libraryItemDTO);
            default:
                throw new RuntimeException("Invalid item type");
        }
    }

    @Override
    public CollectionModel<EntityModel<LibraryItemDTO>> getShelf(ItemTypes itemType)
    {
        switch (itemType)
        {
            case BOOK:
                return CollectionModel.of(bookService.getBookShelf());
            case LETTER:
                return CollectionModel.of(letterService.getLetterShelf());
            case MAGAZINE:
                return CollectionModel.of(magazineService.getMagazineShelf());
            case NEWSPAPER:
                return CollectionModel.of(newspaperService.getNewspaperShelf());
            default:
                throw new RuntimeException("Invalid item type");
        }
    }

    @Override
    public CollectionModel<EntityModel<LibraryItemDTO>> getAllShelves()
    {
        List<EntityModel<LibraryItemDTO>> allShelves = new ArrayList<>();
        Stream.of(bookService.getBookShelf(), magazineService.getMagazineShelf(), newspaperService.getNewspaperShelf(),
            letterService.getLetterShelf()).forEach(allShelves::addAll);
        return CollectionModel.of(allShelves, linkTo(methodOn(LibraryItemController.class).getAllShelves()).withSelfRel());
    }

    @Override
    public ResponseEntity<LibraryItemDTO> updateItem(final Long itemToUpdateId, final LibraryItemDTO newItem, ItemTypes itemType)
        throws ItemNotFoundException
    {
        switch (itemType)
        {
            case BOOK:
                return bookService.updateBook(itemToUpdateId, (BookDTO) newItem);
            case LETTER:
                return letterService.updateLetter(itemToUpdateId, (LetterDTO) newItem);
            case MAGAZINE:
                return magazineService.updateMagazine(itemToUpdateId, (MagazineDTO) newItem);
            case NEWSPAPER:
                return newspaperService.updateNewspaper(itemToUpdateId, (NewspaperDTO) newItem);
            default:
                throw new RuntimeException("Invalid item type");
        }
    }

    @Override
    public ResponseEntity<LibraryItemDTO> removeItem(final Long itemToRemoveId, ItemTypes itemType)
    {
        switch (itemType)
        {
            case BOOK:
                return bookService.removeItem(itemToRemoveId);
            case LETTER:
                return letterService.removeItem(itemToRemoveId);
            case MAGAZINE:
                return magazineService.removeItem(itemToRemoveId);
            case NEWSPAPER:
                return newspaperService.removeItem(itemToRemoveId);
            default:
                throw new RuntimeException("Invalid item type");
        }
    }

    @Override
    public ResponseEntity<LibraryItemDTO> getLibraryItem(final Long itemId, ItemTypes itemType)
    {
        switch (itemType)
        {
            case BOOK:
                return bookService.getLibraryItem(itemId);
            case LETTER:
                return letterService.getLibraryItem(itemId);
            case MAGAZINE:
                return magazineService.getLibraryItem(itemId);
            case NEWSPAPER:
                return newspaperService.getLibraryItem(itemId);
            default:
                throw new RuntimeException("Invalid item type");
        }
    }
}
