package com.springboot.bookstoreDB.mapper;

import java.util.List;

import com.springboot.bookstoreDB.dto.CommentDTO;
import com.springboot.bookstoreDB.entity.CommentEntity;
import lombok.Generated;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Generated
@Mapper(componentModel = "spring")
public interface CommentMapper
{
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    CommentDTO modelToDto(CommentEntity commentEntity);

    List<CommentDTO> modelToCommentDtos(List<CommentEntity> commentEntities);

    CommentEntity dtoToModel(CommentDTO commentDTO);

    List<CommentDTO> commentEntityListToCommentDtoList(List<CommentEntity> list);
}
