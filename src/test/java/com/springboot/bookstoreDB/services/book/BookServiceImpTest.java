package com.springboot.bookstoreDB.services.book;

import java.util.List;

import com.springboot.bookstoreDB.dto.BookDTO;
import com.springboot.bookstoreDB.dto.LibraryItemDTO;
import com.springboot.bookstoreDB.entity.BookEntity;
import com.springboot.bookstoreDB.entity.LibraryItemEntity;
import com.springboot.bookstoreDB.exceptions.ItemNotFoundException;
import com.springboot.bookstoreDB.mapper.BookMapper;
import com.springboot.bookstoreDB.models.LibraryItemModelAssembler;
import com.springboot.bookstoreDB.repository.LibraryItemRepository;
import com.springboot.bookstoreDB.strategies.add.IAddLibraryItem;
import com.springboot.bookstoreDB.strategies.delete.IDeleteLibraryItem;
import com.springboot.bookstoreDB.strategies.get.IGetLibraryItem;
import com.springboot.bookstoreDB.strategies.update.IUpdateLibraryItem;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BookServiceImpTest
{
    private final BookDTO bookDTO = new BookDTO();

    private final BookEntity bookEntity = new BookEntity();

    private final BookDTO expectedBookDTO = new BookDTO();

    private final LibraryItemEntity libraryItemEntity = new BookEntity();

    @InjectMocks
    private BookServiceImp bookService;

    @Mock
    private BookMapper bookMapper;

    @Mock
    private LibraryItemRepository repository;

    @Mock
    private IDeleteLibraryItem deleteLibraryItem;

    @Mock
    private IAddLibraryItem addLibraryItem;

    @Mock
    private IGetLibraryItem getLibraryItem;

    @Mock
    private LibraryItemModelAssembler assembler;

    @Mock
    private IUpdateLibraryItem updateLibraryItem;

    @Test
    public void getLibraryItemTest()
    {
        when(getLibraryItem.getItem(1L)).thenReturn(libraryItemEntity);
        when(bookMapper.modelToDto((BookEntity) libraryItemEntity, true)).thenReturn(bookDTO);
        ResponseEntity<LibraryItemDTO> response = bookService.getLibraryItem(1L);
        assertEquals(bookDTO, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void removeItemTest()
    {
        when(repository.findBookById(1L)).thenReturn(bookEntity);
        assertEquals(HttpStatus.NO_CONTENT, bookService.removeItem(1L).getStatusCode());
    }

    @Test
    public void removeItemNotFoundTest()
    {
        when(repository.findBookById(1L)).thenReturn(null);
        assertThrows(ItemNotFoundException.class, () -> bookService.removeItem(1L));
    }

    @Test
    public void addBookTest()
    {
        when(bookMapper.dtoToModel(bookDTO)).thenReturn(bookEntity);
        when(addLibraryItem.addItem(bookEntity)).thenReturn(libraryItemEntity);
        when(bookMapper.modelToDto((BookEntity) libraryItemEntity, false)).thenReturn(expectedBookDTO);
        ResponseEntity<LibraryItemDTO> response = bookService.addBook(bookDTO);
        assertEquals(expectedBookDTO, response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void getBookShelfTest()
    {
        List<BookEntity> bookEntityList = List.of(bookEntity);
        EntityModel<LibraryItemDTO> model = EntityModel.of(bookDTO);
        List<EntityModel<LibraryItemDTO>> expectedList = List.of(model);
        when(repository.findAllByBooks()).thenReturn(bookEntityList);
        when(bookMapper.modelToDto(bookEntity, false)).thenReturn(bookDTO);
        when(assembler.toModel(bookDTO)).thenReturn(model);
        assertEquals(expectedList, bookService.getBookShelf());
    }

    @Test
    public void updateBookTest()
    {
        BookEntity newBookEntity = new BookEntity();
        when(repository.findBookById(1L)).thenReturn(bookEntity);
        when(bookMapper.dtoToModel(bookDTO)).thenReturn(newBookEntity);
        when(updateLibraryItem.updateItem(bookEntity, newBookEntity)).thenReturn(libraryItemEntity);
        when(bookMapper.modelToDto((BookEntity) libraryItemEntity, false)).thenReturn(expectedBookDTO);
        ResponseEntity<LibraryItemDTO> response = bookService.updateBook(1L, bookDTO);
        assertEquals(expectedBookDTO, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void updateBookNotFoundTest()
    {
        BookDTO newBookDto = new BookDTO();
        when(repository.findBookById(1L)).thenReturn(null);
        assertThrows(ItemNotFoundException.class, () -> bookService.updateBook(1L, newBookDto));
    }
}
