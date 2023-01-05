package com.springboot.bookstoreDB.controllers;

import java.util.List;

import com.springboot.bookstoreDB.dto.CommentDTO;
import com.springboot.bookstoreDB.mapper.BookMapper;
import com.springboot.bookstoreDB.repository.CommentRepository;
import com.springboot.bookstoreDB.repository.LibraryItemRepository;
import com.springboot.bookstoreDB.services.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api/v1/item")
public class CommentController
{
    private final CommentService commentService;

    private final CommentRepository commentRepository;

    private final BookMapper mapper;

    private final LibraryItemRepository libraryItemRepository;

    @PostMapping("/{itemId}/comment")
    public ResponseEntity<CommentDTO> addComment(@PathVariable Long itemId,
        @RequestBody CommentDTO commentRequest)
    {
        return commentService.addComment(commentRequest, itemId);
    }

    @DeleteMapping("/{itemId}/comment/{commentId}")
    ResponseEntity<CommentDTO> deleteComment(@PathVariable Long itemId, @PathVariable Long commentId)
    {
        return commentService.removeComment(commentId, itemId);
    }

    @GetMapping("/{itemId}/comment")
    public ResponseEntity<List<CommentDTO>> getCommentsOfOneItem(@PathVariable Long itemId)
    {
        return commentService.getCommentsFromItem(itemId);
    }
}
