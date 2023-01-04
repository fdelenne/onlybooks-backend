package com.springboot.bookstoreDB.services.letter;

import java.util.List;

import com.springboot.bookstoreDB.dto.LetterDTO;
import com.springboot.bookstoreDB.dto.LibraryItemDTO;
import com.springboot.bookstoreDB.entity.LetterEntity;
import com.springboot.bookstoreDB.entity.LibraryItemEntity;
import com.springboot.bookstoreDB.exceptions.ItemNotFoundException;
import com.springboot.bookstoreDB.mapper.LetterMapper;
import com.springboot.bookstoreDB.models.LibraryItemModelAssembler;
import com.springboot.bookstoreDB.repository.LibraryItemRepository;
import com.springboot.bookstoreDB.strategies.add.IAddLibraryItem;
import com.springboot.bookstoreDB.strategies.delete.IDeleteLibraryItem;
import com.springboot.bookstoreDB.strategies.get.IGetLibraryItem;
import com.springboot.bookstoreDB.strategies.update.IUpdateLibraryItem;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class LetterServiceImpTest
{
    private final LetterDTO letterDTO = new LetterDTO();

    private final LetterEntity letterEntity = new LetterEntity();

    private final LetterDTO expectedLetterDTO = new LetterDTO();

    private final LibraryItemEntity libraryItemEntity = new LetterEntity();

    @InjectMocks
    private LetterServiceImp letterService;

    @Mock
    private LetterMapper letterMapper;

    @Mock
    private LibraryItemRepository repository;

    @Mock
    private IDeleteLibraryItem deleteLibraryItem;

    @Mock
    private IAddLibraryItem addLibraryItem;

    @Mock
    private LibraryItemModelAssembler assembler;

    @Mock
    private IUpdateLibraryItem updateLibraryItem;

    @Mock
    private IGetLibraryItem getLibraryItem;

    @Test
    public void getLibraryLetterTest()
    {
        when(getLibraryItem.getItem(1L)).thenReturn(libraryItemEntity);
        when(letterMapper.modelToDto((LetterEntity) libraryItemEntity, true)).thenReturn(letterDTO);
        ResponseEntity<LibraryItemDTO> response = letterService.getLibraryItem(1L);
        assertEquals(letterDTO, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void removeLetterTest()
    {
        when(repository.findLetterById(1L)).thenReturn(letterEntity);
        assertEquals(HttpStatus.NO_CONTENT, letterService.removeItem(1L).getStatusCode());
    }

    @Test
    public void removeLetterNotFoundTest()
    {
        when(repository.findLetterById(1L)).thenReturn(null);
        assertThrows(ItemNotFoundException.class, () -> letterService.removeItem(1L));
    }

    @Test
    public void addLetterTest()
    {

        when(letterMapper.dtoToModel(letterDTO)).thenReturn(letterEntity);
        when(addLibraryItem.addItem(letterEntity)).thenReturn(libraryItemEntity);
        when(letterMapper.modelToDto((LetterEntity) libraryItemEntity, false)).thenReturn(expectedLetterDTO);
        ResponseEntity<LibraryItemDTO> response = letterService.addLetter(letterDTO);
        assertEquals(expectedLetterDTO, response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void getLetterShelfTest()
    {
        List<LetterEntity> letterEntityList = List.of(letterEntity);
        EntityModel<LibraryItemDTO> model = EntityModel.of(letterDTO);
        List<EntityModel<LibraryItemDTO>> expectedList = List.of(model);
        when(repository.findAllByLetters()).thenReturn(letterEntityList);
        when(letterMapper.modelToDto(letterEntity, false)).thenReturn(letterDTO);
        when(assembler.toModel(letterDTO)).thenReturn(model);
        assertEquals(expectedList, letterService.getLetterShelf());
    }

    @Test
    public void updateLetterTest()
    {
        LetterEntity newLetterEntity = new LetterEntity();
        when(repository.findLetterById(1L)).thenReturn(letterEntity);
        when(letterMapper.dtoToModel(letterDTO)).thenReturn(newLetterEntity);
        when(updateLibraryItem.updateItem(letterEntity, newLetterEntity)).thenReturn(libraryItemEntity);
        when(letterMapper.modelToDto((LetterEntity) libraryItemEntity, false)).thenReturn(expectedLetterDTO);
        ResponseEntity<LibraryItemDTO> response = letterService.updateLetter(1L, letterDTO);
        assertEquals(expectedLetterDTO, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void updateLetterNotFoundTest()
    {
        LetterDTO newLetterDto = new LetterDTO();
        when(repository.findBookById(1L)).thenReturn(null);
        assertThrows(ItemNotFoundException.class, () -> letterService.updateLetter(1L, newLetterDto));
    }
}
