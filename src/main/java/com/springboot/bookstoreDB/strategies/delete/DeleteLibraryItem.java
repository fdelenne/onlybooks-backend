package com.springboot.bookstoreDB.strategies.delete;

import com.springboot.bookstoreDB.entity.LibraryItemEntity;
import com.springboot.bookstoreDB.repository.LibraryItemRepository;
import lombok.Generated;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DeleteLibraryItem implements IDeleteLibraryItem
{
    private final LibraryItemRepository repository;

    @Override
    @Generated
    public void deleteItem(final LibraryItemEntity itemToDelete)
    {
        repository.delete(itemToDelete);
    }
}
