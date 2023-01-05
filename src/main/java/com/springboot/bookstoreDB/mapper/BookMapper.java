package com.springboot.bookstoreDB.mapper;

import java.util.ArrayList;
import java.util.List;

import com.springboot.bookstoreDB.dto.BookDTO;
import com.springboot.bookstoreDB.dto.CommentDTO;
import com.springboot.bookstoreDB.entity.BookEntity;
import com.springboot.bookstoreDB.entity.CommentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class BookMapper
{
    public BookDTO modelToDto(BookEntity bookEntity, Boolean includeComments)
    {
        if (bookEntity == null)
        {
            return null;
        }

        BookDTO bookDTO = new BookDTO();

        bookDTO.setId(bookEntity.getId());
        bookDTO.setTitle(bookEntity.getTitle());
        bookDTO.setAuthor(bookEntity.getAuthor());
        bookDTO.setDate(bookEntity.getDate());
        bookDTO.setPage(bookEntity.getPage());
        bookDTO.setQuantity(bookEntity.getQuantity());
        bookDTO.setDescription(bookEntity.getDescription());
        bookDTO.setUrlImage(bookEntity.getUrlImage());
        bookDTO.setISBN(bookEntity.getISBN());
        bookDTO.setPublisher(bookEntity.getPublisher());
        bookDTO.setCategory(bookEntity.getCategory());

        if (includeComments)
        {
            bookDTO.setAmountOfComments(bookEntity.getCommentList().size());
        }

        return bookDTO;
    }

    public BookEntity dtoToModel(BookDTO bookDTO)
    {
        if (bookDTO == null)
        {
            return null;
        }

        BookEntity bookEntity = new BookEntity();

        bookEntity.setCommentList(commentDTOListToCommentEntityList(bookDTO.getCommentList()));
        bookEntity.setId(bookDTO.getId());
        bookEntity.setTitle(bookDTO.getTitle());
        bookEntity.setAuthor(bookDTO.getAuthor());
        bookEntity.setDate(bookDTO.getDate());
        bookEntity.setPage(bookDTO.getPage());
        bookEntity.setQuantity(bookDTO.getQuantity());
        bookEntity.setDescription(bookDTO.getDescription());
        bookEntity.setUrlImage(bookDTO.getUrlImage());
        bookEntity.setISBN(bookDTO.getISBN());
        bookEntity.setPublisher(bookDTO.getPublisher());
        bookEntity.setCategory(bookDTO.getCategory());

        return bookEntity;
    }

    protected CommentDTO commentEntityToCommentDTO(CommentEntity commentEntity)
    {
        if (commentEntity == null)
        {
            return null;
        }

        CommentDTO commentDTO = new CommentDTO();

        commentDTO.setId(commentEntity.getId());
        commentDTO.setAuthor(commentEntity.getAuthor());
        commentDTO.setComment(commentEntity.getComment());
        commentDTO.setDate(commentEntity.getDate());

        return commentDTO;
    }

    protected List<CommentDTO> commentEntityListToCommentDTOList(List<CommentEntity> list)
    {
        if (list == null)
        {
            return null;
        }

        List<CommentDTO> commentDtoList = new ArrayList<CommentDTO>(list.size());
        for (CommentEntity commentEntity : list)
        {
            commentDtoList.add(commentEntityToCommentDTO(commentEntity));
        }

        return commentDtoList;
    }

    protected CommentEntity commentDTOToCommentEntity(CommentDTO commentDTO)
    {
        if (commentDTO == null)
        {
            return null;
        }

        CommentEntity commentEntity = new CommentEntity();

        commentEntity.setId(commentDTO.getId());
        commentEntity.setAuthor(commentDTO.getAuthor());
        commentEntity.setComment(commentDTO.getComment());
        commentEntity.setDate(commentDTO.getDate());

        return commentEntity;
    }

    protected List<CommentEntity> commentDTOListToCommentEntityList(List<CommentDTO> list)
    {
        if (list == null)
        {
            return null;
        }

        List<CommentEntity> commentEntityList = new ArrayList<CommentEntity>(list.size());
        for (CommentDTO commentDTO : list)
        {
            commentEntityList.add(commentDTOToCommentEntity(commentDTO));
        }

        return commentEntityList;
    }
}