package com.springboot.bookstoreDB.strategies.get;

import java.util.Optional;

import com.springboot.bookstoreDB.entity.BookEntity;
import com.springboot.bookstoreDB.entity.LibraryItemEntity;
import com.springboot.bookstoreDB.exceptions.ItemNotFoundException;
import com.springboot.bookstoreDB.repository.LibraryItemRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class GetLibraryItemTest
{
    private final LibraryItemEntity libraryItemEntity = new BookEntity();

    @InjectMocks
    private GetLibraryItem getLibraryItem;

    @Mock
    private LibraryItemRepository repository;

    @Test
    public void getItemTest()
    {
        when(repository.findById(1L)).thenReturn(Optional.of(libraryItemEntity));
        assertEquals(libraryItemEntity, getLibraryItem.getItem(1L));
    }

    @Test
    public void getItemNotFoundTest()
    {
        when(repository.findById(1L)).thenReturn(null);
        assertThrows(ItemNotFoundException.class, () -> getLibraryItem.getItem(null));
    }
}
