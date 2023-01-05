package com.springboot.bookstoreDB.mapper;

import java.util.ArrayList;
import java.util.List;

import com.springboot.bookstoreDB.dto.CommentDTO;
import com.springboot.bookstoreDB.dto.NewspaperDTO;
import com.springboot.bookstoreDB.entity.CommentEntity;
import com.springboot.bookstoreDB.entity.NewspaperEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class NewspaperMapper
{
    public NewspaperDTO modelToDto(NewspaperEntity newspaperEntity, Boolean includeComments)
    {
        if (newspaperEntity == null)
        {
            return null;
        }

        NewspaperDTO newspaperDTO = new NewspaperDTO();

        newspaperDTO.setId(newspaperEntity.getId());
        newspaperDTO.setTitle(newspaperEntity.getTitle());
        newspaperDTO.setAuthor(newspaperEntity.getAuthor());
        newspaperDTO.setDate(newspaperEntity.getDate());
        newspaperDTO.setPage(newspaperEntity.getPage());
        newspaperDTO.setQuantity(newspaperEntity.getQuantity());
        newspaperDTO.setDescription(newspaperEntity.getDescription());
        newspaperDTO.setUrlImage(newspaperEntity.getUrlImage());
        newspaperDTO.setCommentList(commentEntityListToCommentDTOList(newspaperEntity.getCommentList()));
        newspaperDTO.setArticles(newspaperEntity.getArticles());
        newspaperDTO.setCity(newspaperEntity.getCity());
        if (includeComments)
        {
            newspaperDTO.setAmountOfComments(newspaperEntity.getCommentList().size());
        }

        return newspaperDTO;
    }

    public NewspaperEntity dtoToModel(NewspaperDTO newspaperDTO)
    {
        if (newspaperDTO == null)
        {
            return null;
        }

        NewspaperEntity newspaperEntity = new NewspaperEntity();

        newspaperEntity.setCommentList(commentDTOListToCommentEntityList(newspaperDTO.getCommentList()));
        newspaperEntity.setId(newspaperDTO.getId());
        newspaperEntity.setTitle(newspaperDTO.getTitle());
        newspaperEntity.setAuthor(newspaperDTO.getAuthor());
        newspaperEntity.setDate(newspaperDTO.getDate());
        newspaperEntity.setPage(newspaperDTO.getPage());
        newspaperEntity.setQuantity(newspaperDTO.getQuantity());
        newspaperEntity.setDescription(newspaperDTO.getDescription());
        newspaperEntity.setUrlImage(newspaperDTO.getUrlImage());
        newspaperEntity.setArticles(newspaperDTO.getArticles());
        newspaperEntity.setCity(newspaperDTO.getCity());

        return newspaperEntity;
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
