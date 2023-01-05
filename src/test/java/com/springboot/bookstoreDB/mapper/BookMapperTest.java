package com.springboot.bookstoreDB.mapper;

import java.time.LocalDate;
import java.util.List;

import com.springboot.bookstoreDB.dto.CommentDTO;
import com.springboot.bookstoreDB.entity.CommentEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BookMapperTest
{
    @Autowired
    private BookMapperImpl bookMapper;

    @Test
    public void modelToDtoNullTest()
    {
        assertEquals(null, bookMapper.modelToDto(null, true));
    }

    @Test
    public void dtoToModelNullTest()
    {
        assertEquals(null, bookMapper.dtoToModel(null));
    }

    @Test
    public void commentEntityToCommentDtoNullTest()
    {
        assertEquals(null, bookMapper.commentEntityToCommentDTO(null));
    }

    @Test
    public void commentDTOToCommentEntityNullTest()
    {
        assertEquals(null, bookMapper.commentDTOToCommentEntity(null));
    }

    @Test
    public void commentDTOToCommentEntityTest()
    {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(1L);
        commentDTO.setAuthor("Autor");
        commentDTO.setComment("Comentario");
        commentDTO.setDate(LocalDate.now());
        CommentEntity commentEntity = bookMapper.commentDTOToCommentEntity(commentDTO);
        assertEquals(commentDTO.getId(), commentEntity.getId());
        assertEquals(commentDTO.getAuthor(), commentEntity.getAuthor());
        assertEquals(commentDTO.getComment(), commentEntity.getComment());
        assertEquals(commentDTO.getDate(), commentEntity.getDate());
    }

    @Test
    public void commentDTOListToCommentEntityListNullTest()
    {
        assertEquals(null, bookMapper.commentDTOListToCommentEntityList(null));
    }

    @Test
    public void commentDTOListToCommentEntityListTest()
    {
        CommentDTO commentDTO1 = new CommentDTO();
        commentDTO1.setId(1L);
        commentDTO1.setAuthor("Autor");
        commentDTO1.setComment("Comentario");
        commentDTO1.setDate(LocalDate.now());

        CommentDTO commentDTO2 = new CommentDTO();
        commentDTO2.setId(2L);
        commentDTO2.setAuthor("Autor 2");
        commentDTO2.setComment("Comentario 3");
        commentDTO2.setDate(LocalDate.now());

        List<CommentDTO> commentDTOList = List.of(commentDTO1, commentDTO2);
        List<CommentEntity> commentEntityList = bookMapper.commentDTOListToCommentEntityList(commentDTOList);

        assertEquals(commentDTOList.get(0).getId(), commentEntityList.get(0).getId());
        assertEquals(commentDTOList.get(0).getAuthor(), commentEntityList.get(0).getAuthor());
        assertEquals(commentDTOList.get(0).getComment(), commentEntityList.get(0).getComment());
        assertEquals(commentDTOList.get(0).getDate(), commentEntityList.get(0).getDate());

        assertEquals(commentDTOList.get(1).getId(), commentEntityList.get(1).getId());
        assertEquals(commentDTOList.get(1).getAuthor(), commentEntityList.get(1).getAuthor());
        assertEquals(commentDTOList.get(1).getComment(), commentEntityList.get(1).getComment());
        assertEquals(commentDTOList.get(1).getDate(), commentEntityList.get(1).getDate());
    }

    @Test
    public void commentEntityToCommentDTOTest(){
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setId(1L);
        commentEntity.setAuthor("Autor");
        commentEntity.setComment("Comentario");
        commentEntity.setDate(LocalDate.now());

        CommentDTO commentDTO = bookMapper.commentEntityToCommentDTO(commentEntity);

        assertEquals(commentEntity.getId(), commentDTO.getId());
        assertEquals(commentEntity.getAuthor(), commentDTO.getAuthor());
        assertEquals(commentEntity.getComment(), commentDTO.getComment());
        assertEquals(commentEntity.getDate(), commentDTO.getDate());
    }

    @Test
    public void commentEntityListToCommentDTOListNullTest(){
        assertEquals(null, bookMapper.commentEntityListToCommentDTOList(null));
    }
    @Test
    public void commentEntityListToCommentDTOListTest(){
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setId(1L);
        commentEntity.setAuthor("Autor");
        commentEntity.setComment("Comentario");
        commentEntity.setDate(LocalDate.now());

        CommentEntity commentEntity2 = new CommentEntity();
        commentEntity2.setId(2L);
        commentEntity2.setAuthor("Autor 2 ");
        commentEntity2.setComment("Comentario 2");
        commentEntity2.setDate(LocalDate.now());

        List<CommentEntity> commentEntityList = List.of(commentEntity,commentEntity2);
        List<CommentDTO> commentDTOList = bookMapper.commentEntityListToCommentDTOList(commentEntityList);

        assertEquals(commentEntityList.get(0).getId(), commentDTOList.get(0).getId() );
        assertEquals(commentEntityList.get(0).getAuthor(), commentDTOList.get(0).getAuthor());
        assertEquals(commentEntityList.get(0).getComment(), commentDTOList.get(0).getComment());
        assertEquals(commentEntityList.get(0).getDate(), commentDTOList.get(0).getDate());

        assertEquals(commentEntityList.get(1).getId(), commentDTOList.get(1).getId());
        assertEquals(commentEntityList.get(1).getAuthor(), commentDTOList.get(1).getAuthor());
        assertEquals(commentEntityList.get(1).getComment(), commentDTOList.get(1).getComment());
        assertEquals(commentEntityList.get(1).getDate(), commentDTOList.get(1).getDate());

    }

}
