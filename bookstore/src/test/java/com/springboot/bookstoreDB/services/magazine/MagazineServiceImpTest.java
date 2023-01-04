package com.springboot.bookstoreDB.services.magazine;

import java.util.List;

import com.springboot.bookstoreDB.dto.LibraryItemDTO;
import com.springboot.bookstoreDB.dto.MagazineDTO;
import com.springboot.bookstoreDB.entity.LibraryItemEntity;
import com.springboot.bookstoreDB.entity.MagazineEntity;
import com.springboot.bookstoreDB.exceptions.ItemNotFoundException;
import com.springboot.bookstoreDB.mapper.MagazineMapper;
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
public class MagazineServiceImpTest
{
    private final MagazineDTO magazineDTO = new MagazineDTO();

    private final MagazineEntity magazineEntity = new MagazineEntity();

    private final MagazineDTO expectedMagazineDTO = new MagazineDTO();

    private final LibraryItemEntity libraryItemEntity = new MagazineEntity();

    @InjectMocks
    private MagazineServiceImp magazineService;

    @Mock
    private MagazineMapper magazineMapper;

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
    public void getLibraryMagazineTest()
    {
        when(getLibraryItem.getItem(1L)).thenReturn(libraryItemEntity);
        when(magazineMapper.modelToDto((MagazineEntity) libraryItemEntity, true)).thenReturn(magazineDTO);
        ResponseEntity<LibraryItemDTO> response = magazineService.getLibraryItem(1L);
        assertEquals(magazineDTO, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void removeMagazineTest()
    {
        when(repository.findMagazineById(1L)).thenReturn(magazineEntity);
        assertEquals(HttpStatus.NO_CONTENT, magazineService.removeItem(1L).getStatusCode());
    }

    @Test
    public void removeMagazineNotFoundTest()
    {
        when(repository.findBookById(1L)).thenReturn(null);
        assertThrows(ItemNotFoundException.class, () -> magazineService.removeItem(1L));
    }

    @Test
    public void addMagazineTest()
    {

        when(magazineMapper.dtoToModel(magazineDTO)).thenReturn(magazineEntity);
        when(addLibraryItem.addItem(magazineEntity)).thenReturn(libraryItemEntity);
        when(magazineMapper.modelToDto((MagazineEntity) libraryItemEntity, false)).thenReturn(expectedMagazineDTO);
        ResponseEntity<LibraryItemDTO> response = magazineService.addMagazine(magazineDTO);
        assertEquals(expectedMagazineDTO, response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void getMagazineShelfTest()
    {
        List<MagazineEntity> magazineEntityList = List.of(magazineEntity);
        EntityModel<LibraryItemDTO> model = EntityModel.of(magazineDTO);
        List<EntityModel<LibraryItemDTO>> expectedList = List.of(model);
        when(repository.findAllByMagazines()).thenReturn(magazineEntityList);
        when(magazineMapper.modelToDto(magazineEntity, false)).thenReturn(magazineDTO);
        when(assembler.toModel(magazineDTO)).thenReturn(model);
        assertEquals(expectedList, magazineService.getMagazineShelf());
    }

    @Test
    public void updateMagazineTest()
    {
        MagazineEntity newMagazineEntity = new MagazineEntity();
        when(repository.findMagazineById(1L)).thenReturn(magazineEntity);
        when(magazineMapper.dtoToModel(magazineDTO)).thenReturn(newMagazineEntity);
        when(updateLibraryItem.updateItem(magazineEntity, newMagazineEntity)).thenReturn(libraryItemEntity);
        when(magazineMapper.modelToDto((MagazineEntity) libraryItemEntity, false)).thenReturn(expectedMagazineDTO);
        ResponseEntity<LibraryItemDTO> response = magazineService.updateMagazine(1L, magazineDTO);
        assertEquals(expectedMagazineDTO, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void updateMagazineNotFoundTest()
    {
        MagazineDTO newMagazineDto = new MagazineDTO();
        when(repository.findBookById(1L)).thenReturn(null);
        assertThrows(ItemNotFoundException.class, () -> magazineService.updateMagazine(1L, newMagazineDto));
    }
}
