package com.springboot.bookstoreDB.mapper;

import java.util.ArrayList;
import java.util.List;

import com.springboot.bookstoreDB.dto.CommentDTO;
import com.springboot.bookstoreDB.dto.MagazineDTO;
import com.springboot.bookstoreDB.entity.CommentEntity;
import com.springboot.bookstoreDB.entity.MagazineEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class MagazineMapper
{
    public MagazineDTO modelToDto(MagazineEntity magazineEntity, Boolean includeComments)
    {
        if (magazineEntity == null)
        {
            return null;
        }

        MagazineDTO magazineDTO = new MagazineDTO();

        magazineDTO.setId(magazineEntity.getId());
        magazineDTO.setTitle(magazineEntity.getTitle());
        magazineDTO.setAuthor(magazineEntity.getAuthor());
        magazineDTO.setDate(magazineEntity.getDate());
        magazineDTO.setPage(magazineEntity.getPage());
        magazineDTO.setQuantity(magazineEntity.getQuantity());
        magazineDTO.setDescription(magazineEntity.getDescription());
        magazineDTO.setUrlImage(magazineEntity.getUrlImage());
        magazineDTO.setArticles(magazineEntity.getArticles());
        magazineDTO.setCategory(magazineEntity.getCategory());
        if (includeComments)
        {
            magazineDTO.setAmountOfComments(magazineEntity.getCommentList().size());
        }

        return magazineDTO;
    }

    public MagazineEntity dtoToModel(MagazineDTO magazineDTO)
    {
        if (magazineDTO == null)
        {
            return null;
        }

        MagazineEntity magazineEntity = new MagazineEntity();

        magazineEntity.setCommentList(commentDTOListToCommentEntityList(magazineDTO.getCommentList()));
        magazineEntity.setId(magazineDTO.getId());
        magazineEntity.setTitle(magazineDTO.getTitle());
        magazineEntity.setAuthor(magazineDTO.getAuthor());
        magazineEntity.setDate(magazineDTO.getDate());
        magazineEntity.setPage(magazineDTO.getPage());
        magazineEntity.setQuantity(magazineDTO.getQuantity());
        magazineEntity.setDescription(magazineDTO.getDescription());
        magazineEntity.setUrlImage(magazineDTO.getUrlImage());
        magazineEntity.setArticles(magazineDTO.getArticles());
        magazineEntity.setCategory(magazineDTO.getCategory());

        return magazineEntity;
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
