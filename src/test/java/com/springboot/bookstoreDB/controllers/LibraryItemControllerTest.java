package com.springboot.bookstoreDB.controllers;

import com.springboot.bookstoreDB.TestDatabase;
import com.springboot.bookstoreDB.entity.BookEntity;
import com.springboot.bookstoreDB.entity.CommentEntity;
import com.springboot.bookstoreDB.entity.LetterEntity;
import com.springboot.bookstoreDB.entity.LibraryItemEntity;
import com.springboot.bookstoreDB.entity.MagazineEntity;
import com.springboot.bookstoreDB.entity.NewspaperEntity;
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
public class LibraryItemControllerTest
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
    public void addBookTest() throws Exception
    {

        StringBuilder body = new StringBuilder();
        body.append("{\n");
        body.append("\"title\": \"BEST BOOK\",\n");
        body.append("\"author\": \"Book book\",\n");
        body.append("\"date\": \"2022-09-22\",\n");
        body.append("\"page\": 23,\n");
        body.append("\"quantity\": 1,\n");
        body.append("\"description\": \"A COOL BOOK \",\n");
        body.append("\"urlImage\": \"http://book.com\",\n");
        body.append("\"itemType\": \"BOOK\",\n");
        body.append("\"publisher\": 4,\n");
        body.append("\"category\": \"BOOK\",\n");
        body.append("\"isbn\": 23 \n");
        body.append("}");

        mvc.perform(MockMvcRequestBuilders.post("/api/v1/Item/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(body.toString())).andExpect(status().isCreated());
    }

    @Test
    public void addMagazineTest() throws Exception
    {
        StringBuilder body = new StringBuilder();
        body.append("{\n");
        body.append("\"title\": \"BEST MAGAZINE\",\n");
        body.append("\"author\": \"MAG MAG\",\n");
        body.append("\"date\": \"2022-09-22\",\n");
        body.append("\"page\": 23,\n");
        body.append("\"quantity\": 1,\n");
        body.append("\"description\": \"A COOL MAGAZINE \",\n");
        body.append("\"urlImage\": \"http://magazine.com\",\n");
        body.append("\"itemType\": \"MAGAZINE\",\n");
        body.append("\"articles\": 4,\n");
        body.append("\"category\": \"MAGA\"\n");
        body.append("}");

        mvc.perform(MockMvcRequestBuilders.post("/api/v1/Item/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(body.toString())).andExpect(status().isCreated());
    }

    @Test
    public void addLetterTest() throws Exception
    {
        StringBuilder body = new StringBuilder();
        body.append("{\n");
        body.append("\"title\": \"BEST LETTER\",\n");
        body.append("\"author\": \"leta leta\",\n");
        body.append("\"date\": \"2022-09-22\",\n");
        body.append("\"page\": 23,\n");
        body.append("\"quantity\": 1,\n");
        body.append("\"description\": \"A COOL LETTER \",\n");
        body.append("\"urlImage\": \"http://letter.com\",\n");
        body.append("\"itemType\": \"LETTER\",\n");
        body.append("\"origin\": \"GDL\",\n");
        body.append("\"destination\": \"GDL\",\n");
        body.append("\"receiver\": \"LETTER ME\" \n");
        body.append("}");

        mvc.perform(MockMvcRequestBuilders.post("/api/v1/Item/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(body.toString())).andExpect(status().isCreated());
    }

    @Test
    public void addNewspaperTest() throws Exception
    {
        StringBuilder body = new StringBuilder();
        body.append("{\n");
        body.append("\"title\": \"BEST NEWSPAPER\",\n");
        body.append("\"author\": \"niu peipa \",\n");
        body.append("\"date\": \"2022-09-22\",\n");
        body.append("\"page\": 23,\n");
        body.append("\"quantity\": 1,\n");
        body.append("\"description\": \"A COOL NEWSPAPER \",\n");
        body.append("\"urlImage\": \"http://newspaper.com\",\n");
        body.append("\"itemType\": \"NEWSPAPER\",\n");
        body.append("\"articles\": 4,\n");
        body.append("\"city\": \"GDL\"\n");
        body.append("}");

        mvc.perform(MockMvcRequestBuilders.post("/api/v1/Item/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(body.toString())).andExpect(status().isCreated());
    }

    @Test
    public void getOneBookTest() throws Exception
    {
        LibraryItemEntity libraryItemEntity = new BookEntity();
        libraryItemRepository.save(libraryItemEntity);
        final Long itemId = libraryItemEntity.getId();
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/Item/{itemType}/{id}", "BOOK", itemId))
            .andExpect(status().isOk()).andReturn();
    }

    @Test
    public void getOneMagazineTest() throws Exception
    {
        LibraryItemEntity libraryItemEntity = new MagazineEntity();
        libraryItemRepository.save(libraryItemEntity);
        final Long itemId = libraryItemEntity.getId();
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/Item/{itemType}/{id}", "MAGAZINE", itemId))
            .andExpect(status().isOk()).andReturn();
    }

    @Test
    public void getOneLetterTest() throws Exception
    {
        LibraryItemEntity libraryItemEntity = new LetterEntity();
        libraryItemRepository.save(libraryItemEntity);
        final Long itemId = libraryItemEntity.getId();
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/Item/{itemType}/{id}", "LETTER", itemId))
            .andExpect(status().isOk()).andReturn();
    }

    @Test
    public void getOneNewspaperTest() throws Exception
    {
        LibraryItemEntity libraryItemEntity = new NewspaperEntity();
        libraryItemRepository.save(libraryItemEntity);
        final Long itemId = libraryItemEntity.getId();
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/Item/{itemType}/{id}", "NEWSPAPER", itemId))
            .andExpect(status().isOk()).andReturn();
    }

    @Test
    public void getBookShelfTest() throws Exception
    {
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/Item/getShelf/{itemType}", "BOOK"))
            .andExpect(status().isOk()).andReturn();
    }

    @Test
    public void getLetterShelfTest() throws Exception
    {
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/Item/getShelf/{itemType}", "LETTER"))
            .andExpect(status().isOk()).andReturn();
    }

    @Test
    public void getMagazineShelfTest() throws Exception
    {
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/Item/getShelf/{itemType}", "MAGAZINE"))
            .andExpect(status().isOk()).andReturn();
    }

    @Test
    public void getNewspaperShelfTest() throws Exception
    {
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/Item/getShelf/{itemType}", "NEWSPAPER"))
            .andExpect(status().isOk()).andReturn();
    }

    @Test
    public void getAllShelvesTest() throws Exception
    {
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/Item/"))
            .andExpect(status().isOk()).andReturn();
    }

    @Test
    public void updateItemTest() throws Exception
    {
        LibraryItemEntity libraryItemEntity = new BookEntity();
        libraryItemRepository.save(libraryItemEntity);
        final Long itemId = libraryItemEntity.getId();

        CommentEntity commentEntity = new CommentEntity();
        libraryItemEntity.addToCommentList(commentEntity);
        commentRepository.save(commentEntity);

        StringBuilder body = new StringBuilder();
        body.append("{\n");
        body.append("\"title\": \"BEST BOOK\",\n");
        body.append("\"author\": \"MAG MAG\",\n");
        body.append("\"date\": \"2022-09-22\",\n");
        body.append("\"page\": 23,\n");
        body.append("\"quantity\": 1,\n");
        body.append("\"description\": \"A COOL MAGAZINE \",\n");
        body.append("\"urlImage\": \"http://magazine.com\",\n");
        body.append("\"itemType\": \"BOOK\",\n");
        body.append("\"publisher\": 4,\n");
        body.append("\"category\": \"MAGA\",\n");
        body.append("\"isbn\": 23 \n");
        body.append("}");
        mvc.perform(MockMvcRequestBuilders.put("/api/v1/Item/{itemType}/{id}", "BOOK", itemId)
            .contentType(MediaType.APPLICATION_JSON)
            .content(body.toString())).andExpect(status().isOk()).andReturn();
    }

    @Test
    public void deleteItemTest() throws Exception
    {
        LibraryItemEntity libraryItemEntity = new BookEntity();
        libraryItemRepository.save(libraryItemEntity);
        final Long itemId = libraryItemEntity.getId();
        mvc.perform(MockMvcRequestBuilders.delete("/api/v1/Item/{itemType}/{id}", "BOOK", itemId))
            .andExpect(status().isNoContent()).andReturn();
    }
}
