package com.springboot.bookstoreDB.services.magazine;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
@Transactional
public class MagazineServiceImp implements MagazineService
{
    private final IUpdateLibraryItem updateLibraryItem;

    private final IAddLibraryItem addLibraryItem;

    private final IDeleteLibraryItem deleteLibraryItem;

    private final IGetLibraryItem getLibraryItem;

    private final LibraryItemModelAssembler assembler;

    private final MagazineMapper magazineMapper;

    private final LibraryItemRepository repository;

    @Override
    public ResponseEntity<LibraryItemDTO> addMagazine(final MagazineDTO magazineDTO)
    {
        MagazineEntity magazineEntityRequest = magazineMapper.dtoToModel(magazineDTO);
        LibraryItemEntity libraryItemEntity = addLibraryItem.addItem(magazineEntityRequest);
        MagazineDTO magazineResponse = magazineMapper.modelToDto((MagazineEntity) libraryItemEntity, false);
        return new ResponseEntity<>(magazineResponse, HttpStatus.CREATED);
    }

    @Override
    public List<EntityModel<LibraryItemDTO>> getMagazineShelf()
    {

        return repository.findAllByMagazines().stream().map(magazineEntity -> magazineMapper.modelToDto(magazineEntity, false))
            .map(assembler::toModel).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<LibraryItemDTO> updateMagazine(final Long itemToUpdateId, final MagazineDTO newItem) throws ItemNotFoundException
    {
        MagazineEntity magazineToUpdate = Optional.ofNullable(repository.findMagazineById(itemToUpdateId))
            .orElseThrow(() -> new ItemNotFoundException(itemToUpdateId));
        MagazineEntity newMagazineRequestEntity = magazineMapper.dtoToModel(newItem);
        repository.updateMagazineById(newMagazineRequestEntity.getArticles(), newMagazineRequestEntity.getCategory(), itemToUpdateId);
        LibraryItemEntity libraryItemEntity = updateLibraryItem.updateItem(magazineToUpdate, newMagazineRequestEntity);
        MagazineDTO newMagazineResponse = magazineMapper.modelToDto((MagazineEntity) libraryItemEntity, false);
        return new ResponseEntity<>(newMagazineResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<LibraryItemDTO> removeItem(final Long itemToRemoveId)
    {
        MagazineEntity magazineToDelete = Optional.ofNullable(repository.findMagazineById(itemToRemoveId))
            .orElseThrow(() -> new ItemNotFoundException(itemToRemoveId));
        deleteLibraryItem.deleteItem(magazineToDelete);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<LibraryItemDTO> getLibraryItem(final Long itemId)
    {
        MagazineEntity magazineEntity = (MagazineEntity) getLibraryItem.getItem(itemId);
        MagazineDTO magazineResponse = magazineMapper.modelToDto(magazineEntity, true);
        return new ResponseEntity<>(magazineResponse, HttpStatus.OK);
    }
}
