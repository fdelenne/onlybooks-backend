package com.springboot.bookstoreDB.services.newspaper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
public class NewspaperServiceImp implements NewspaperService
{
    private final IUpdateLibraryItem updateLibraryItem;

    private final IAddLibraryItem addLibraryItem;

    private final IDeleteLibraryItem deleteLibraryItem;

    private final IGetLibraryItem getLibraryItem;

    private final LibraryItemModelAssembler assembler;

    private final LibraryItemRepository repository;

    private final NewspaperMapper newspaperMapper;

    @Override
    public ResponseEntity<LibraryItemDTO> addNewspaper(final NewspaperDTO newspaperDTO)
    {
        NewspaperEntity newspaperEntityRequest = newspaperMapper.dtoToModel(newspaperDTO);
        LibraryItemEntity libraryItemEntity = addLibraryItem.addItem(newspaperEntityRequest);
        NewspaperDTO newspaperResponse = newspaperMapper.modelToDto((NewspaperEntity) libraryItemEntity, false);
        return new ResponseEntity<>(newspaperResponse, HttpStatus.CREATED);
    }

    @Override
    public List<EntityModel<LibraryItemDTO>> getNewspaperShelf()
    {
        return repository.findAllByNewspapers().stream().map(newspaperEntity -> newspaperMapper.modelToDto(newspaperEntity, false))
            .map(assembler::toModel).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<LibraryItemDTO> updateNewspaper(final Long itemToUpdateId, final NewspaperDTO newItem)
        throws ItemNotFoundException
    {
        NewspaperEntity newspaperToUpdate = Optional.ofNullable(repository.findNewspaperById(itemToUpdateId))
            .orElseThrow(() -> new ItemNotFoundException(itemToUpdateId));
        NewspaperEntity newspaperRequestEntity = newspaperMapper.dtoToModel(newItem);
        repository.updateNewspaperById(newspaperRequestEntity.getArticles(), newspaperRequestEntity.getCity(), itemToUpdateId);
        LibraryItemEntity libraryItemEntity = updateLibraryItem.updateItem(newspaperToUpdate, newspaperRequestEntity);
        NewspaperDTO newspaperResponse = newspaperMapper.modelToDto((NewspaperEntity) libraryItemEntity, false);
        return new ResponseEntity<>(newspaperResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<LibraryItemDTO> removeItem(final Long itemToRemoveId)
    {
        NewspaperEntity newspaperToDelete = Optional.ofNullable(repository.findNewspaperById(itemToRemoveId))
            .orElseThrow(() -> new ItemNotFoundException(itemToRemoveId));
        deleteLibraryItem.deleteItem(newspaperToDelete);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<LibraryItemDTO> getLibraryItem(final Long itemId)
    {
        NewspaperEntity newspaperEntity = (NewspaperEntity) getLibraryItem.getItem(itemId);
        NewspaperDTO newspaperResponse = newspaperMapper.modelToDto(newspaperEntity, true);
        return new ResponseEntity<>(newspaperResponse, HttpStatus.OK);
    }
}
