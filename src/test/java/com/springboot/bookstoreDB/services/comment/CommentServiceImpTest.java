package com.springboot.bookstoreDB.services.comment;

import java.util.List;
import java.util.Optional;

import com.springboot.bookstoreDB.dto.CommentDTO;
import com.springboot.bookstoreDB.entity.BookEntity;
import com.springboot.bookstoreDB.entity.CommentEntity;
import com.springboot.bookstoreDB.entity.LibraryItemEntity;
import com.springboot.bookstoreDB.exceptions.CommentNotFoundException;
import com.springboot.bookstoreDB.exceptions.ItemNotFoundException;
import com.springboot.bookstoreDB.mapper.CommentMapper;
import com.springboot.bookstoreDB.repository.CommentRepository;
import com.springboot.bookstoreDB.repository.LibraryItemRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@Transactional
@SpringBootTest
public class CommentServiceImpTest
{
    private final CommentEntity commentEntity = new CommentEntity();

    private final CommentDTO commentDTO = new CommentDTO();

    private final LibraryItemEntity libraryItemEntity = new BookEntity();

    @InjectMocks
    private CommentServiceImp commentService;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private LibraryItemRepository libraryItemRepository;

    @Mock
    private CommentMapper commentMapper;

    @Test
    public void addCommentTest()
    {
        when(commentMapper.dtoToModel(commentDTO)).thenReturn(commentEntity);
        when(libraryItemRepository.findById(1L)).thenReturn(Optional.of(libraryItemEntity));
        when(commentMapper.modelToDto(commentEntity)).thenReturn(commentDTO);
        assertEquals(new ResponseEntity<>(commentDTO, HttpStatus.CREATED), commentService.addComment(commentDTO, 1L));
    }

    @Test
    public void addCommentThrowsTest()
    {
        assertThrows(ItemNotFoundException.class, () -> commentService.addComment(commentDTO, 1L));
    }

    @Test
    public void addAndRemoveCommentTest()
    {
        when(libraryItemRepository.findById(1L)).thenReturn(Optional.of(libraryItemEntity));
        when(commentRepository.findById(1L)).thenReturn(Optional.of(commentEntity));
        commentService.addComment(commentDTO, 1L);
        assertEquals(new ResponseEntity<>(HttpStatus.NO_CONTENT), commentService.removeComment(1L, 1L));
    }

    @Test
    public void throwWhenRemoveBookNotFound()
    {
        when(commentRepository.findById(1L)).thenReturn(Optional.of(commentEntity));
        assertThrows(ItemNotFoundException.class, () -> commentService.removeComment(1L, 1L));
    }

    @Test
    public void throwWhenRemoveCommentNotFound()
    {
        when(libraryItemRepository.findById(1L)).thenReturn(Optional.of(libraryItemEntity));
        assertThrows(CommentNotFoundException.class, () -> commentService.removeComment(1L, 1L));
    }

    @Test
    public void getCommentsFromItemTest()
    {
        CommentEntity comment = new CommentEntity();
        CommentDTO commentDTO = new CommentDTO();

        List<Long> allCommentsId = List.of(1L);
        List<CommentEntity> commentEntityList = List.of(comment);
        List<CommentDTO> commentDtoList = List.of(commentDTO);

        when(commentRepository.findAllCommentsFromItem(1L)).thenReturn(allCommentsId);
        when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));
        when(commentMapper.commentEntityListToCommentDtoList(commentEntityList)).thenReturn(commentDtoList);

        assertEquals(new ResponseEntity<>(commentDtoList, HttpStatus.OK), commentService.getCommentsFromItem(1L));
    }
}
