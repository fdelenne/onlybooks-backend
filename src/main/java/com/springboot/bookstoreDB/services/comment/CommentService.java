package com.springboot.bookstoreDB.services.comment;

import java.util.List;

import com.springboot.bookstoreDB.dto.CommentDTO;
import org.springframework.http.ResponseEntity;

public interface CommentService
{
    ResponseEntity<CommentDTO> addComment(final CommentDTO commentDTO, final Long itemId);

    ResponseEntity<CommentDTO> removeComment(final Long commentId, final Long itemId);

    ResponseEntity<List<CommentDTO>> getCommentsFromItem(final Long itemId);
}
