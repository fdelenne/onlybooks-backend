package com.springboot.bookstoreDB.strategies.get;

import com.springboot.bookstoreDB.entity.LibraryItemEntity;
import com.springboot.bookstoreDB.exceptions.ItemNotFoundException;
import com.springboot.bookstoreDB.repository.LibraryItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class GetLibraryItem implements IGetLibraryItem
{
    private final LibraryItemRepository repository;

    @Override
    public LibraryItemEntity getItem(final Long itemId)
    {
        return repository.findById(itemId).orElseThrow(() -> new ItemNotFoundException(itemId));
    }
}
