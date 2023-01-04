package com.springboot.bookstoreDB.strategies.update;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.springboot.bookstoreDB.entity.LibraryItemEntity;
import com.springboot.bookstoreDB.utils.ItemTypes;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional(noRollbackForClassName = { "LibraryItemEntity", "BookEntity", "LetterEntity", "MagazineEntity", "NewspaperEntity" })
public class UpdateLibraryItem implements IUpdateLibraryItem
{
    private final EntityManager entityManager;

    @Override
    @Modifying
    public LibraryItemEntity updateItem(final LibraryItemEntity oldLibraryItemEntity, LibraryItemEntity newLibraryItemEntity)
    {
        Query query = entityManager.createNativeQuery(
                "UPDATE " + ItemTypes.getItemEntityType(oldLibraryItemEntity)
                    + " SET author = :author, date = :date, description = :description , page = :page, quantity = :quantity ,title = :title, url_image = :urlImage WHERE id = :id").
            setParameter("author", newLibraryItemEntity.getAuthor()).
            setParameter("date", newLibraryItemEntity.getDate()).
            setParameter("description", newLibraryItemEntity.getDescription()).
            setParameter("page", newLibraryItemEntity.getPage()).
            setParameter("quantity", newLibraryItemEntity.getQuantity()).
            setParameter("title", newLibraryItemEntity.getTitle()).
            setParameter("urlImage", newLibraryItemEntity.getUrlImage()).
            setParameter("id", oldLibraryItemEntity.getId());
        query.executeUpdate();
        entityManager.refresh(oldLibraryItemEntity);
        return oldLibraryItemEntity;
    }
}
