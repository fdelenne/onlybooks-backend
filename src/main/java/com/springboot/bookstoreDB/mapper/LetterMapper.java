package com.springboot.bookstoreDB.mapper;

import java.util.ArrayList;
import java.util.List;

import com.springboot.bookstoreDB.dto.CommentDTO;
import com.springboot.bookstoreDB.dto.LetterDTO;
import com.springboot.bookstoreDB.entity.CommentEntity;
import com.springboot.bookstoreDB.entity.LetterEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class LetterMapper
{
    public LetterDTO modelToDto(LetterEntity letterEntity, Boolean includeComments)
    {
        if (letterEntity == null)
        {
            return null;
        }

        LetterDTO letterDTO = new LetterDTO();

        letterDTO.setId(letterEntity.getId());
        letterDTO.setTitle(letterEntity.getTitle());
        letterDTO.setAuthor(letterEntity.getAuthor());
        letterDTO.setDate(letterEntity.getDate());
        letterDTO.setPage(letterEntity.getPage());
        letterDTO.setQuantity(letterEntity.getQuantity());
        letterDTO.setDescription(letterEntity.getDescription());
        letterDTO.setUrlImage(letterEntity.getUrlImage());
        letterDTO.setReceiver(letterEntity.getReceiver());
        letterDTO.setOrigin(letterEntity.getOrigin());
        letterDTO.setDestination(letterEntity.getDestination());
        if (includeComments)
        {
            letterDTO.setAmountOfComments(letterEntity.getCommentList().size());
        }

        return letterDTO;
    }

    public LetterEntity dtoToModel(LetterDTO letterDTO)
    {
        if (letterDTO == null)
        {
            return null;
        }

        LetterEntity letterEntity = new LetterEntity();

        letterEntity.setCommentList(commentDTOListToCommentEntityList(letterDTO.getCommentList()));
        letterEntity.setId(letterDTO.getId());
        letterEntity.setTitle(letterDTO.getTitle());
        letterEntity.setAuthor(letterDTO.getAuthor());
        letterEntity.setDate(letterDTO.getDate());
        letterEntity.setPage(letterDTO.getPage());
        letterEntity.setQuantity(letterDTO.getQuantity());
        letterEntity.setDescription(letterDTO.getDescription());
        letterEntity.setUrlImage(letterDTO.getUrlImage());
        letterEntity.setReceiver(letterDTO.getReceiver());
        letterEntity.setOrigin(letterDTO.getOrigin());
        letterEntity.setDestination(letterDTO.getDestination());

        return letterEntity;
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

        List<CommentDTO> list1 = new ArrayList<CommentDTO>(list.size());
        for (CommentEntity commentEntity : list)
        {
            list1.add(commentEntityToCommentDTO(commentEntity));
        }

        return list1;
    }

    protected CommentEntity commentDTOToCommentEntity(CommentDTO commentDTO)
    {
        if (commentDTO == null)
        {
            return null;
        }

        CommentEntity commentEntity = new CommentEntity();

        commentEntity.setDate(commentDTO.getDate());
        commentEntity.setId(commentDTO.getId());
        commentEntity.setAuthor(commentDTO.getAuthor());
        commentEntity.setComment(commentDTO.getComment());

        return commentEntity;
    }

    protected List<CommentEntity> commentDTOListToCommentEntityList(List<CommentDTO> list)
    {
        if (list == null)
        {
            return null;
        }

        List<CommentEntity> list1 = new ArrayList<CommentEntity>(list.size());
        for (CommentDTO commentDTO : list)
        {
            list1.add(commentDTOToCommentEntity(commentDTO));
        }

        return list1;
    }
}