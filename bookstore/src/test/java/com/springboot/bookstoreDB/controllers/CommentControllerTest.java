package com.springboot.bookstoreDB.controllers;

import com.springboot.bookstoreDB.TestDatabase;
import com.springboot.bookstoreDB.entity.BookEntity;
import com.springboot.bookstoreDB.entity.CommentEntity;
import com.springboot.bookstoreDB.entity.LibraryItemEntity;
import com.springboot.bookstoreDB.repository.CommentRepository;
import com.springboot.bookstoreDB.repository.LibraryItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@Import(TestDatabase.class)
public class CommentControllerTest
{
    @Autowired
    private LibraryItemController libraryItemController;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private LibraryItemRepository libraryItemRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void addItemTest() throws Exception
    {
        StringBuilder body = new StringBuilder();
        body.append("{\n");
        body.append("\"author\": \"string\",\n");
        body.append("\"comment\": \"string\",\n");
        body.append("\"date\": \"2022-11-30\"\n");
        body.append("}");

        LibraryItemEntity libraryItemEntity = new BookEntity();
        libraryItemRepository.save(libraryItemEntity);
        final Long itemId = libraryItemEntity.getId();

        mvc.perform(MockMvcRequestBuilders.post("/api/v1/Comment/{itemId}", itemId)
            .contentType(MediaType.APPLICATION_JSON)
            .content(body.toString())).andExpect(status().isCreated());
    }


    @Test
    public void deleteItemTest() throws Exception
    {
        LibraryItemEntity libraryItemEntity = new BookEntity();
        libraryItemRepository.save(libraryItemEntity);
        final Long itemId = libraryItemEntity.getId();

        CommentEntity commentEntity = new CommentEntity();
        libraryItemEntity.addToCommentList(commentEntity);
        commentRepository.save(commentEntity);
        final Long commentId = commentEntity.getId();
        mvc.perform(MockMvcRequestBuilders.delete("/api/v1/Comment/{itemId}/{commentId}", itemId, commentId))
            .andExpect(status().isNoContent()).andReturn();
    }

    @Test
    public void getCommentsOfOneItemTest() throws Exception
    {
        LibraryItemEntity libraryItemEntity = new BookEntity();
        libraryItemRepository.save(libraryItemEntity);
        final Long itemId = libraryItemEntity.getId();

        CommentEntity commentEntity = new CommentEntity();
        libraryItemEntity.addToCommentList(commentEntity);
        commentRepository.save(commentEntity);

        mvc.perform(MockMvcRequestBuilders.get("/api/v1/Comment/{itemId}", itemId)).andExpect(status().isOk());
    }


}
