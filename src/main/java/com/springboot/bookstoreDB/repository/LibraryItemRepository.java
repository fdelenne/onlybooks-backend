package com.springboot.bookstoreDB.repository;

import java.util.List;

import com.springboot.bookstoreDB.entity.BookEntity;
import com.springboot.bookstoreDB.entity.LetterEntity;
import com.springboot.bookstoreDB.entity.LibraryItemEntity;
import com.springboot.bookstoreDB.entity.MagazineEntity;
import com.springboot.bookstoreDB.entity.NewspaperEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryItemRepository extends JpaRepository<LibraryItemEntity, Long>
{
    @Query(value = "SELECT *, 0 AS clazz_ FROM book", nativeQuery = true)
    List<BookEntity> findAllByBooks();

    @Query(value = "SELECT *, 0 AS clazz_ FROM letter", nativeQuery = true)
    List<LetterEntity> findAllByLetters();

    @Query(value = "SELECT *, 0 AS clazz_ FROM newspaper", nativeQuery = true)
    List<NewspaperEntity> findAllByNewspapers();

    @Query(value = "SELECT *, 0 AS clazz_ FROM magazine", nativeQuery = true)
    List<MagazineEntity> findAllByMagazines();

    @Query(value = "SELECT *, 0 AS clazz__ FROM book WHERE id= :id", nativeQuery = true)
    BookEntity findBookById(@Param("id") Long id);

    @Query(value = "SELECT *, 0 AS clazz__ FROM letter WHERE id= :id", nativeQuery = true)
    LetterEntity findLetterById(@Param("id") Long id);

    @Query(value = "SELECT *, 0 AS clazz__ FROM magazine WHERE id= :id", nativeQuery = true)
    MagazineEntity findMagazineById(@Param("id") Long id);

    @Query(value = "SELECT *, 0 AS clazz__ FROM newspaper WHERE id= :id", nativeQuery = true)
    NewspaperEntity findNewspaperById(@Param("id") Long id);

    @Modifying
    @Query(value = "UPDATE book SET isbn = ?1, category = ?2, publisher = ?3 WHERE id = ?4", nativeQuery = true)
    void updateBookById(@Param("isbn") Long isbn, @Param("category") String category, @Param("publisher") String publisher,
        @Param("id") Long id);

    @Modifying
    @Query(value = "UPDATE magazine SET articles = ?1, category = ?2 WHERE id = ?3", nativeQuery = true)
    void updateMagazineById(@Param("articles") int articles, @Param("category") String category, @Param("id") Long id);

    @Modifying
    @Query(value = "UPDATE newspaper SET articles = ?1, city = ?2 WHERE id = ?3", nativeQuery = true)
    void updateNewspaperById(@Param("articles") int articles, @Param("city") String city, @Param("id") Long id);

    @Modifying
    @Query(value = "UPDATE letter SET destination = ?1, origin = ?2, receiver = ?3 WHERE id = ?4", nativeQuery = true)
    void updateLetterById(@Param("destination") String destination, @Param("origen") String origen, @Param("receiver") String receiver,
        @Param("id") Long id);
}
