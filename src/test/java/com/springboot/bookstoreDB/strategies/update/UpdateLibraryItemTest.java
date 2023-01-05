package com.springboot.bookstoreDB.strategies.update;

import java.time.LocalDate;

import com.springboot.bookstoreDB.entity.BookEntity;
import com.springboot.bookstoreDB.entity.LibraryItemEntity;
import com.springboot.bookstoreDB.exceptions.ItemNotFoundException;
import com.springboot.bookstoreDB.repository.LibraryItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
@SpringBootTest
public class UpdateLibraryItemTest
{
    private final LibraryItemEntity newLibraryItemEntity = new BookEntity();

    @Autowired
    UpdateLibraryItem updateLibraryItem;

    @Autowired
    LibraryItemRepository repository;

    @Test
    public void updateItemTest()
    {
        LibraryItemEntity libraryItemEntity = new BookEntity();
        repository.save(libraryItemEntity);
        final Long itemId = libraryItemEntity.getId();
        LibraryItemEntity oldLibraryItemEntity = repository.findById(itemId).orElseThrow(() -> new ItemNotFoundException(itemId));
        newLibraryItemEntity.setAuthor("Maria Padilla");
        newLibraryItemEntity.setDate(LocalDate.of(2020, 01, 01));
        newLibraryItemEntity.setDescription("Soy un test");
        newLibraryItemEntity.setPage(22);
        newLibraryItemEntity.setQuantity(1);
        newLibraryItemEntity.setTitle("Test");
        newLibraryItemEntity.setUrlImage("test.com");
        oldLibraryItemEntity = updateLibraryItem.updateItem(oldLibraryItemEntity, newLibraryItemEntity);
        assertEquals(newLibraryItemEntity.getAuthor(), oldLibraryItemEntity.getAuthor());
        assertEquals(newLibraryItemEntity.getDate(), oldLibraryItemEntity.getDate());
        assertEquals(newLibraryItemEntity.getDescription(), oldLibraryItemEntity.getDescription());
        assertEquals(newLibraryItemEntity.getPage(), oldLibraryItemEntity.getPage());
        assertEquals(newLibraryItemEntity.getQuantity(), oldLibraryItemEntity.getQuantity());
        assertEquals(newLibraryItemEntity.getTitle(), oldLibraryItemEntity.getTitle());
        assertEquals(newLibraryItemEntity.getUrlImage(), oldLibraryItemEntity.getUrlImage());
    }
}
