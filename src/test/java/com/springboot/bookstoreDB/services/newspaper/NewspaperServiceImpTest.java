package com.springboot.bookstoreDB.services.newspaper;

import java.util.List;

import com.springboot.bookstoreDB.dto.LibraryItemDTO;
import com.springboot.bookstoreDB.dto.NewspaperDTO;
import com.springboot.bookstoreDB.entity.LibraryItemEntity;
import com.springboot.bookstoreDB.entity.NewspaperEntity;
import com.springboot.bookstoreDB.exceptions.ItemNotFoundException;
import com.springboot.bookstoreDB.mapper.NewspaperMapper;
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
public class NewspaperServiceImpTest
{
    private final NewspaperDTO newspaperDTO = new NewspaperDTO();

    private final NewspaperEntity newspaperEntity = new NewspaperEntity();

    private final NewspaperDTO expectedNewspaperDTO = new NewspaperDTO();

    private final LibraryItemEntity libraryItemEntity = new NewspaperEntity();

    @InjectMocks
    private NewspaperServiceImp newspaperService;

    @Mock
    private NewspaperMapper newspaperMapper;

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
    public void getLibraryItemTest()
    {
        when(getLibraryItem.getItem(1L)).thenReturn(libraryItemEntity);
        when(newspaperMapper.modelToDto((NewspaperEntity) libraryItemEntity, true)).thenReturn(newspaperDTO);
        ResponseEntity<LibraryItemDTO> response = newspaperService.getLibraryItem(1L);
        assertEquals(newspaperDTO, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void removeItemTest()
    {
        when(repository.findNewspaperById(1L)).thenReturn(newspaperEntity);
        assertEquals(HttpStatus.NO_CONTENT, newspaperService.removeItem(1L).getStatusCode());
    }

    @Test
    public void removeItemNotFoundTest()
    {
        when(repository.findBookById(1L)).thenReturn(null);
        assertThrows(ItemNotFoundException.class, () -> newspaperService.removeItem(1L));
    }

    @Test
    public void addBookTest()
    {

        when(newspaperMapper.dtoToModel(newspaperDTO)).thenReturn(newspaperEntity);
        when(addLibraryItem.addItem(newspaperEntity)).thenReturn(libraryItemEntity);
        when(newspaperMapper.modelToDto((NewspaperEntity) libraryItemEntity, false)).thenReturn(expectedNewspaperDTO);
        ResponseEntity<LibraryItemDTO> response = newspaperService.addNewspaper(newspaperDTO);
        assertEquals(expectedNewspaperDTO, response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void getBookShelfTest()
    {
        List<NewspaperEntity> newspaperEntityList = List.of(newspaperEntity);
        EntityModel<LibraryItemDTO> model = EntityModel.of(newspaperDTO);
        List<EntityModel<LibraryItemDTO>> expectedList = List.of(model);
        when(repository.findAllByNewspapers()).thenReturn(newspaperEntityList);
        when(newspaperMapper.modelToDto(newspaperEntity, false)).thenReturn(newspaperDTO);
        when(assembler.toModel(newspaperDTO)).thenReturn(model);
        assertEquals(expectedList, newspaperService.getNewspaperShelf());
    }

    @Test
    public void updateBookTest()
    {
        NewspaperEntity newNewspaperEntity = new NewspaperEntity();
        when(repository.findNewspaperById(1L)).thenReturn(newspaperEntity);
        when(newspaperMapper.dtoToModel(newspaperDTO)).thenReturn(newNewspaperEntity);
        when(updateLibraryItem.updateItem(newspaperEntity, newNewspaperEntity)).thenReturn(libraryItemEntity);
        when(newspaperMapper.modelToDto((NewspaperEntity) libraryItemEntity, false)).thenReturn(expectedNewspaperDTO);
        ResponseEntity<LibraryItemDTO> response = newspaperService.updateNewspaper(1L, newspaperDTO);
        assertEquals(expectedNewspaperDTO, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void updateBookNotFoundTest()
    {
        NewspaperDTO newNewspaperDTO = new NewspaperDTO();
        when(repository.findBookById(1L)).thenReturn(null);
        assertThrows(ItemNotFoundException.class, () -> newspaperService.updateNewspaper(1L, newNewspaperDTO));
    }
}
