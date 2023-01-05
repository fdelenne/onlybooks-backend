package com.springboot.bookstoreDB.services.book;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.springboot.bookstoreDB.dto.BookDTO;
import com.springboot.bookstoreDB.dto.LibraryItemDTO;
import com.springboot.bookstoreDB.entity.BookEntity;
import com.springboot.bookstoreDB.entity.LibraryItemEntity;
import com.springboot.bookstoreDB.exceptions.ItemNotFoundException;
import com.springboot.bookstoreDB.mapper.BookMapper;
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
public class BookServiceImp implements BookService
{
    private final IUpdateLibraryItem updateLibraryItem;

    private final IAddLibraryItem addLibraryItem;

    private final IDeleteLibraryItem deleteLibraryItem;

    private final IGetLibraryItem getLibraryItem;

    private final LibraryItemModelAssembler assembler;

    private final LibraryItemRepository repository;

    private final BookMapper bookMapper;

    @Override
    public ResponseEntity<LibraryItemDTO> addBook(final BookDTO bookDTO)
    {
        BookEntity bookEntityRequest = bookMapper.dtoToModel(bookDTO);
        LibraryItemEntity libraryItemEntity = addLibraryItem.addItem(bookEntityRequest);
        BookDTO bookResponse = bookMapper.modelToDto((BookEntity) libraryItemEntity, false);
        return new ResponseEntity<>(bookResponse, HttpStatus.CREATED);
    }

    @Override
    public List<EntityModel<LibraryItemDTO>> getBookShelf()
    {
        return repository.findAllByBooks().stream().map(bookEntity -> bookMapper.modelToDto(bookEntity, false)).map(assembler::toModel)
            .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<LibraryItemDTO> updateBook(final Long itemToUpdateId, final BookDTO newItem)
    {

        BookEntity bookToUpdate = Optional.ofNullable(repository.findBookById(itemToUpdateId))
            .orElseThrow(() -> new ItemNotFoundException(itemToUpdateId));
        BookEntity newBookEntity = bookMapper.dtoToModel(newItem);
        repository.updateBookById(newBookEntity.getISBN(), newBookEntity.getCategory(), newBookEntity.getPublisher(), itemToUpdateId);
        LibraryItemEntity libraryItemEntity = updateLibraryItem.updateItem(bookToUpdate, newBookEntity);
        BookDTO newBookResponse = bookMapper.modelToDto((BookEntity) libraryItemEntity, false);
        return new ResponseEntity<>(newBookResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<LibraryItemDTO> removeItem(final Long itemToDeleteId)
    {
        BookEntity bookToDelete = Optional.ofNullable(repository.findBookById(itemToDeleteId))
            .orElseThrow(() -> new ItemNotFoundException(itemToDeleteId));
        deleteLibraryItem.deleteItem(bookToDelete);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<LibraryItemDTO> getLibraryItem(final Long itemId)
    {
        BookEntity bookEntity = (BookEntity) getLibraryItem.getItem(itemId);
        BookDTO bookResponse = bookMapper.modelToDto(bookEntity, true);
        return new ResponseEntity<>(bookResponse, HttpStatus.OK);
    }
}
