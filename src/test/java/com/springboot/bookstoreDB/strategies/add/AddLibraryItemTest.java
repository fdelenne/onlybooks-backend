package com.springboot.bookstoreDB.strategies.add;

import java.util.List;

import com.springboot.bookstoreDB.entity.BookEntity;
import com.springboot.bookstoreDB.entity.LibraryItemEntity;
import com.springboot.bookstoreDB.repository.LibraryItemRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AddLibraryItemTest
{
    private final LibraryItemEntity libraryItemEntity = new BookEntity();

    private final LibraryItemEntity existingLibraryItem = new BookEntity();

    @InjectMocks
    private AddLibraryItem addLibraryItem;

    @Mock
    private LibraryItemRepository repository;

    @Test
    public void addDuplicatedItemTest()
    {
        existingLibraryItem.setQuantity(1);
        libraryItemEntity.setQuantity(1);
        ExampleMatcher modelMatcher = ExampleMatcher.matching()
            .withIgnorePaths("id", "quantity");
        Example<LibraryItemEntity> matchedItem = Example.of(libraryItemEntity, modelMatcher);
        when(repository.exists(matchedItem)).thenReturn(true);
        when(repository.findAll(matchedItem)).thenReturn(List.of(existingLibraryItem));
        when(repository.save(existingLibraryItem)).thenReturn(existingLibraryItem);
        assertEquals(2, addLibraryItem.addItem(libraryItemEntity).getQuantity());
    }

    @Test
    public void addItemTest()
    {
        existingLibraryItem.setQuantity(1);
        libraryItemEntity.setQuantity(1);
        ExampleMatcher modelMatcher = ExampleMatcher.matching()
            .withIgnorePaths("id", "quantity");
        Example<LibraryItemEntity> matchedItem = Example.of(libraryItemEntity, modelMatcher);
        when(repository.exists(matchedItem)).thenReturn(false);
        when(repository.save(libraryItemEntity)).thenReturn(libraryItemEntity);
        assertEquals(libraryItemEntity, addLibraryItem.addItem(libraryItemEntity));
    }
}
