package com.springboot.bookstoreDB.repository;

import java.util.List;

import com.springboot.bookstoreDB.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<CommentEntity, Long>
{
    @Query(value = "SELECT comment_id, 0 AS clazz_  FROM library_item_comments WHERE item_id = :id", nativeQuery = true)
    List<Long> findAllCommentsFromItem(@Param("id") Long id);
}
