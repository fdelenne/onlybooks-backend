package com.springboot.bookstoreDB;

import java.time.LocalDate;

import com.springboot.bookstoreDB.entity.BookEntity;
import com.springboot.bookstoreDB.entity.LetterEntity;
import com.springboot.bookstoreDB.entity.LibraryItemEntity;
import com.springboot.bookstoreDB.entity.MagazineEntity;
import com.springboot.bookstoreDB.repository.LibraryItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestDatabase
{
    private LibraryItemEntity book = new BookEntity();

    private LibraryItemEntity letter = new LetterEntity();

    private LibraryItemEntity magazine = new MagazineEntity();

    @Bean
    public CommandLineRunner initDatabase(LibraryItemRepository repository)
    {
        return args -> {
            book.setTitle("Test book");
            book.setAuthor("Lupe");
            book.setDate(LocalDate.of(2022, 11, 30));
            book.setQuantity(1);
            book.setPage(2);
            book.setUrlImage("https://google.com/");
            book.setDescription("Albo bien");
            repository.save(book);
        };
    }
}
