package com.springboot.bookstoreDB.entity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

@Setter
@Getter
@ToString(callSuper = true)
@Entity(name = "book")
@RequiredArgsConstructor
public class BookEntity extends LibraryItemEntity
{
    @Column(name = "isbn")
    private Long ISBN;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "category")
    private String category;

    @Override
    public boolean equals(final Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
        {
            return false;
        }
        final BookEntity bookEntity = (BookEntity) o;
        return id != null && Objects.equals(id, bookEntity.id);
    }

    @Override
    public int hashCode()
    {
        return getClass().hashCode();
    }
}
