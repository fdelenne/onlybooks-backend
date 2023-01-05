package com.springboot.bookstoreDB.services;

import java.util.List;
import java.util.Optional;

import com.springboot.bookstoreDB.controllers.LibraryItemController;
import com.springboot.bookstoreDB.dto.BookDTO;
import com.springboot.bookstoreDB.dto.LetterDTO;
import com.springboot.bookstoreDB.dto.LibraryItemDTO;
import com.springboot.bookstoreDB.dto.MagazineDTO;
import com.springboot.bookstoreDB.dto.NewspaperDTO;
import com.springboot.bookstoreDB.exceptions.InvalidQuantityException;
import com.springboot.bookstoreDB.services.book.BookService;
import com.springboot.bookstoreDB.services.letter.LetterService;
import com.springboot.bookstoreDB.services.magazine.MagazineService;
import com.springboot.bookstoreDB.services.newspaper.NewspaperService;
import com.springboot.bookstoreDB.utils.ItemTypes;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@SpringBootTest
public class ItemServiceImpTest
{
    private final BookDTO bookDTO = new BookDTO();

    private final LetterDTO letterDTO = new LetterDTO();

    private final MagazineDTO magazineDTO = new MagazineDTO();

    private final NewspaperDTO newspaperDTO = new NewspaperDTO();

    private final LibraryItemDTO libraryItemBookDTO = new BookDTO();

    private final LibraryItemDTO libraryItemLetterDTO = new LetterDTO();

    private final LibraryItemDTO libraryItemMagazineDTO = new MagazineDTO();

    private final LibraryItemDTO libraryItemNewspaperDTO = new NewspaperDTO();

    private final ResponseEntity<LibraryItemDTO> expectedNewspaperResponse = ResponseEntity.of(Optional.of(libraryItemNewspaperDTO));

    private final ResponseEntity<LibraryItemDTO> expectedBookResponse = ResponseEntity.of(Optional.of(libraryItemBookDTO));

    private final ResponseEntity<LibraryItemDTO> expectedLetterResponse = ResponseEntity.of(Optional.of(libraryItemLetterDTO));

    private final ResponseEntity<LibraryItemDTO> expectedMagazineResponse = ResponseEntity.of(Optional.of(libraryItemMagazineDTO));

    @InjectMocks
    private ItemServiceImp itemService;

    @Mock
    private BookService bookService;

    @Mock
    private LetterService letterService;

    @Mock
    private MagazineService magazineService;

    @Mock
    private NewspaperService newspaperService;

    @Test
    public void addLibraryItemQuantityIn0Test()
    {
        assertThrows(InvalidQuantityException.class, () -> itemService.addLibraryItem(newspaperDTO, ItemTypes.NEWSPAPER));
    }

    @Test
    public void addLibraryItemShelfNullItemTypeTest()
    {
        //when(bookService.addBook(bookDTO)).thenReturn(null);
        bookDTO.setQuantity(1);
        assertThrows(RuntimeException.class, () -> itemService.addLibraryItem(bookDTO, null));
    }

    @Test
    public void addLibraryItemForNewspaperTest()
    {
        newspaperDTO.setQuantity(1);

        when(newspaperService.addNewspaper(newspaperDTO)).thenReturn(expectedNewspaperResponse);
        assertEquals(expectedNewspaperResponse, itemService.addLibraryItem(newspaperDTO, ItemTypes.NEWSPAPER));
        assertEquals(HttpStatus.OK, expectedNewspaperResponse.getStatusCode());
    }

    @Test
    public void addLibraryItemForBookTest()
    {
        bookDTO.setQuantity(1);
        when(bookService.addBook(bookDTO)).thenReturn(expectedBookResponse);
        assertEquals(expectedBookResponse, itemService.addLibraryItem(bookDTO, ItemTypes.BOOK));
        assertEquals(HttpStatus.OK, expectedBookResponse.getStatusCode());
    }

    @Test
    public void addLibraryItemForLetterTest()
    {
        letterDTO.setQuantity(1);
        when(letterService.addLetter(letterDTO)).thenReturn(expectedLetterResponse);
        assertEquals(expectedLetterResponse, itemService.addLibraryItem(letterDTO, ItemTypes.LETTER));
        assertEquals(HttpStatus.OK, expectedLetterResponse.getStatusCode());
    }

    @Test
    public void addLibraryItemForMagazineTest()
    {
        magazineDTO.setQuantity(1);
        when(magazineService.addMagazine(magazineDTO)).thenReturn(expectedMagazineResponse);
        assertEquals(expectedMagazineResponse, itemService.addLibraryItem(magazineDTO, ItemTypes.MAGAZINE));
        assertEquals(HttpStatus.OK, expectedMagazineResponse.getStatusCode());
    }

    @Test
    public void getBookShelfTest()
    {
        final List<EntityModel<LibraryItemDTO>> expectedEntityModel = List.of(EntityModel.of(libraryItemBookDTO));
        when(bookService.getBookShelf()).thenReturn(expectedEntityModel);
        assertEquals(CollectionModel.of(expectedEntityModel), itemService.getShelf(ItemTypes.BOOK));
    }

    @Test
    public void getLetterShelfTest()
    {
        final List<EntityModel<LibraryItemDTO>> expectedEntityModel = List.of(EntityModel.of(libraryItemLetterDTO));
        when(letterService.getLetterShelf()).thenReturn(expectedEntityModel);
        assertEquals(CollectionModel.of(expectedEntityModel), itemService.getShelf(ItemTypes.LETTER));
    }

    @Test
    public void getNewspaperShelfTest()
    {
        final List<EntityModel<LibraryItemDTO>> expectedEntityModel = List.of(EntityModel.of(libraryItemNewspaperDTO));
        when(newspaperService.getNewspaperShelf()).thenReturn(expectedEntityModel);
        assertEquals(CollectionModel.of(expectedEntityModel), itemService.getShelf(ItemTypes.NEWSPAPER));
    }

    @Test
    public void getMagazineShelfTest()
    {
        final List<EntityModel<LibraryItemDTO>> expectedEntityModel = List.of(EntityModel.of(libraryItemMagazineDTO));
        when(magazineService.getMagazineShelf()).thenReturn(expectedEntityModel);
        assertEquals(CollectionModel.of(expectedEntityModel), itemService.getShelf(ItemTypes.MAGAZINE));
    }

    @Test
    public void getShelfNullItemTypeTest()
    {
        when(bookService.getBookShelf()).thenReturn(null);
        assertThrows(RuntimeException.class, () -> itemService.getShelf(null));
    }

    @Test
    public void getAllShelvesTest()
    {
        final List<EntityModel<LibraryItemDTO>> expectedMagazineEntityModel = List.of(EntityModel.of(libraryItemMagazineDTO));
        final List<EntityModel<LibraryItemDTO>> expectedNewspaperEntityModel = List.of(EntityModel.of(libraryItemNewspaperDTO));
        final List<EntityModel<LibraryItemDTO>> expectedBookEntityModel = List.of(EntityModel.of(libraryItemBookDTO));
        final List<EntityModel<LibraryItemDTO>> expectedLetterEntityModel = List.of(EntityModel.of(libraryItemLetterDTO));

        List<EntityModel<LibraryItemDTO>> allShelves = List.of(EntityModel.of(libraryItemBookDTO),
            EntityModel.of(libraryItemMagazineDTO), EntityModel.of(libraryItemNewspaperDTO), EntityModel.of(libraryItemLetterDTO));
        when(bookService.getBookShelf()).thenReturn(expectedBookEntityModel);
        when(magazineService.getMagazineShelf()).thenReturn(expectedMagazineEntityModel);
        when(letterService.getLetterShelf()).thenReturn(expectedLetterEntityModel);
        when(newspaperService.getNewspaperShelf()).thenReturn(expectedNewspaperEntityModel);

        assertEquals(CollectionModel.of(allShelves, linkTo(methodOn(LibraryItemController.class).getAllShelves()).withSelfRel()),
            itemService.getAllShelves());
    }

    @Test
    public void updateBookItemTest()
    {
        when(bookService.updateBook(1L, (BookDTO) libraryItemBookDTO)).thenReturn(expectedBookResponse);
        assertEquals(expectedBookResponse, itemService.updateItem(1L, libraryItemBookDTO, ItemTypes.BOOK));
    }

    @Test
    public void updateLetterItemTest()
    {
        when(letterService.updateLetter(1L, (LetterDTO) libraryItemLetterDTO)).thenReturn(expectedLetterResponse);
        assertEquals(expectedLetterResponse, itemService.updateItem(1L, libraryItemLetterDTO, ItemTypes.LETTER));
    }

    @Test
    public void updateNewspaperItemTest()
    {
        when(newspaperService.updateNewspaper(1L, (NewspaperDTO) libraryItemNewspaperDTO)).thenReturn(expectedNewspaperResponse);
        assertEquals(expectedNewspaperResponse, itemService.updateItem(1L, libraryItemNewspaperDTO, ItemTypes.NEWSPAPER));
    }

    @Test
    public void updateMagazineItemTest()
    {
        when(magazineService.updateMagazine(1L, (MagazineDTO) libraryItemMagazineDTO)).thenReturn(expectedMagazineResponse);
        assertEquals(expectedMagazineResponse, itemService.updateItem(1L, libraryItemMagazineDTO, ItemTypes.MAGAZINE));
    }

    @Test
    public void updateItemNullItemTypeTest()
    {
        when(bookService.getBookShelf()).thenReturn(null);
        assertThrows(RuntimeException.class, () -> itemService.updateItem(1L, newspaperDTO, null));
    }

    @Test
    public void removeBookTest()
    {
        when(bookService.removeItem(1L)).thenReturn(ResponseEntity.noContent().build());
        assertEquals(ResponseEntity.noContent().build(), itemService.removeItem(1L, ItemTypes.BOOK));
    }

    @Test
    public void removeLetterTest()
    {
        when(letterService.removeItem(1L)).thenReturn(ResponseEntity.noContent().build());
        assertEquals(ResponseEntity.noContent().build(), itemService.removeItem(1L, ItemTypes.LETTER));
    }

    @Test
    public void removeNewspaperTest()
    {
        when(newspaperService.removeItem(1L)).thenReturn(ResponseEntity.noContent().build());
        assertEquals(ResponseEntity.noContent().build(), itemService.removeItem(1L, ItemTypes.NEWSPAPER));
    }

    @Test
    public void removeMagazineTest()
    {
        when(magazineService.removeItem(1L)).thenReturn(ResponseEntity.noContent().build());
        assertEquals(ResponseEntity.noContent().build(), itemService.removeItem(1L, ItemTypes.MAGAZINE));
    }

    @Test
    public void removeItemNullItemTypeTest()
    {
        assertThrows(RuntimeException.class, () -> itemService.removeItem(1L, null));
    }

    @Test
    public void getBookTest()
    {
        when(bookService.getLibraryItem(1L)).thenReturn(expectedBookResponse);
        assertEquals(expectedBookResponse, itemService.getLibraryItem(1L, ItemTypes.BOOK));
        assertEquals(HttpStatus.OK, itemService.getLibraryItem(1L, ItemTypes.BOOK).getStatusCode());
    }

    @Test
    public void getLetterTest()
    {
        when(letterService.getLibraryItem(1L)).thenReturn(expectedLetterResponse);
        assertEquals(expectedLetterResponse, itemService.getLibraryItem(1L, ItemTypes.LETTER));
        assertEquals(HttpStatus.OK, itemService.getLibraryItem(1L, ItemTypes.LETTER).getStatusCode());
    }

    @Test
    public void getNewspaperTest()
    {
        when(newspaperService.getLibraryItem(1L)).thenReturn(expectedNewspaperResponse);
        assertEquals(expectedNewspaperResponse, itemService.getLibraryItem(1L, ItemTypes.NEWSPAPER));
        assertEquals(HttpStatus.OK, itemService.getLibraryItem(1L, ItemTypes.NEWSPAPER).getStatusCode());
    }

    @Test
    public void getMagazineTest()
    {
        when(magazineService.getLibraryItem(1L)).thenReturn(expectedMagazineResponse);
        assertEquals(expectedMagazineResponse, itemService.getLibraryItem(1L, ItemTypes.MAGAZINE));
        assertEquals(HttpStatus.OK, itemService.getLibraryItem(1L, ItemTypes.MAGAZINE).getStatusCode());
    }

    @Test
    public void getLibraryItemNullItemTypeTest()
    {
        assertThrows(RuntimeException.class, () -> itemService.getLibraryItem(1L, null));
    }
}
