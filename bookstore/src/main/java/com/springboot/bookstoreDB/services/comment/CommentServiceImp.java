package com.springboot.bookstoreDB.services.comment;

import java.util.ArrayList;
import java.util.List;

import com.springboot.bookstoreDB.dto.CommentDTO;
import com.springboot.bookstoreDB.entity.CommentEntity;
import com.springboot.bookstoreDB.entity.LibraryItemEntity;
import com.springboot.bookstoreDB.exceptions.CommentNotFoundException;
import com.springboot.bookstoreDB.exceptions.ItemNotFoundException;
import com.springboot.bookstoreDB.mapper.CommentMapper;
import com.springboot.bookstoreDB.repository.CommentRepository;
import com.springboot.bookstoreDB.repository.LibraryItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class CommentServiceImp implements CommentService
{
    private final CommentRepository commentRepository;

    private final LibraryItemRepository libraryItemRepository;

    private final CommentMapper commentMapper;

    @Override
    public ResponseEntity<CommentDTO> addComment(final CommentDTO commentDTO, final Long itemId)
    {
        final CommentEntity commentEntityRequest = commentMapper.dtoToModel(commentDTO);
        final LibraryItemEntity libraryItemEntity = libraryItemRepository.findById(itemId)
            .orElseThrow(() -> new ItemNotFoundException(itemId));
        libraryItemEntity.addToCommentList(commentEntityRequest);
        commentRepository.save(commentEntityRequest);
        final CommentDTO commentResponse = commentMapper.modelToDto(commentEntityRequest);
        return new ResponseEntity<>(commentResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CommentDTO> removeComment(final Long commentId, final Long itemId)
    {
        LibraryItemEntity libraryItemEntity = libraryItemRepository.findById(itemId).orElseThrow(() -> new ItemNotFoundException(itemId));
        CommentEntity commentToDelete = commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException(commentId));
        libraryItemEntity.getCommentList().remove(commentToDelete);
        commentRepository.delete(commentToDelete);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<CommentDTO>> getCommentsFromItem(final Long itemId)
    {
        List<Long> allCommentsId = commentRepository.findAllCommentsFromItem(itemId);
        List<CommentEntity> commentEntityList = new ArrayList<>();
        for (Long commentId : allCommentsId)
        {
            commentEntityList.add(commentRepository.findById(commentId).orElseThrow());
        }
        List<CommentDTO> commentDtoList = commentMapper.commentEntityListToCommentDtoList(commentEntityList);
        return new ResponseEntity<>(commentDtoList, HttpStatus.OK);
    }
}