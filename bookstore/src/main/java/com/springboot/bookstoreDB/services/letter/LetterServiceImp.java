package com.springboot.bookstoreDB.services.letter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
public class LetterServiceImp implements LetterService
{
    private final IUpdateLibraryItem updateLibraryItem;

    private final IAddLibraryItem addLibraryItem;

    private final IDeleteLibraryItem deleteLibraryItem;

    private final IGetLibraryItem getLibraryItem;

    private final LibraryItemModelAssembler assembler;

    private final LetterMapper letterMapper;

    private final LibraryItemRepository repository;

    @Override
    public ResponseEntity<LibraryItemDTO> addLetter(final LetterDTO letterDTO)
    {
        LetterEntity letterEntityRequest = letterMapper.dtoToModel(letterDTO);
        LibraryItemEntity libraryItemEntity = addLibraryItem.addItem(letterEntityRequest);
        LetterDTO bookResponse = letterMapper.modelToDto((LetterEntity) libraryItemEntity, false);
        return new ResponseEntity<>(bookResponse, HttpStatus.CREATED);
    }

    @Override
    public List<EntityModel<LibraryItemDTO>> getLetterShelf()
    {

        return repository.findAllByLetters().stream().map(letterEntity -> letterMapper.modelToDto(letterEntity, false))
            .map(assembler::toModel).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<LibraryItemDTO> updateLetter(final Long itemToUpdateId, final LetterDTO newItem) throws ItemNotFoundException
    {
        LetterEntity letterToUpdate = Optional.ofNullable(repository.findLetterById(itemToUpdateId))
            .orElseThrow(() -> new ItemNotFoundException(itemToUpdateId));
        LetterEntity newLetterRequestEntity = letterMapper.dtoToModel(newItem);
        repository.updateLetterById(newLetterRequestEntity.getDestination(), newLetterRequestEntity.getOrigin(),
            newLetterRequestEntity.getReceiver(), itemToUpdateId);
        LibraryItemEntity libraryItemEntity = updateLibraryItem.updateItem(letterToUpdate, newLetterRequestEntity);
        LetterDTO newLetterResponse = letterMapper.modelToDto((LetterEntity) libraryItemEntity, false);
        return new ResponseEntity<>(newLetterResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<LibraryItemDTO> removeItem(final Long itemToDeleteId)
    {
        LetterEntity letterToDelete = Optional.ofNullable(repository.findLetterById(itemToDeleteId))
            .orElseThrow(() -> new ItemNotFoundException(itemToDeleteId));
        deleteLibraryItem.deleteItem(letterToDelete);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<LibraryItemDTO> getLibraryItem(final Long itemId)
    {
        LetterEntity letterEntity = (LetterEntity) getLibraryItem.getItem(itemId);
        LetterDTO letterResponse = letterMapper.modelToDto(letterEntity, true);
        return new ResponseEntity<>(letterResponse, HttpStatus.OK);
    }
}
