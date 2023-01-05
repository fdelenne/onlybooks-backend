package com.springboot.bookstoreDB.strategies.add;

import com.springboot.bookstoreDB.entity.LibraryItemEntity;
import com.springboot.bookstoreDB.repository.LibraryItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddLibraryItem implements IAddLibraryItem
{
    private final LibraryItemRepository repository;

    @Override
    public LibraryItemEntity addItem(final LibraryItemEntity libraryItemEntity)
    {
        ExampleMatcher modelMatcher = ExampleMatcher.matching()
            .withIgnorePaths("id", "quantity");
        Example<LibraryItemEntity> matchedItem = Example.of(libraryItemEntity, modelMatcher);
        if (repository.exists(matchedItem))
        {
            LibraryItemEntity existingLibraryItem = repository.findAll(matchedItem).get(0);
            existingLibraryItem.setQuantity(libraryItemEntity.getQuantity() + existingLibraryItem.getQuantity());
            return repository.save(existingLibraryItem);
        }
        else
        {
            return repository.save(libraryItemEntity);
        }
    }
}
