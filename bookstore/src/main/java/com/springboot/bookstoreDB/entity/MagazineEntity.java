package com.springboot.bookstoreDB.entity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

@Setter
@Getter
@ToString(callSuper = true)
@Entity
@Table(name = "magazine")
@RequiredArgsConstructor
public class MagazineEntity extends LibraryItemEntity
{
    @Column(name = "articles")
    private int articles;

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
        final MagazineEntity that = (MagazineEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode()
    {
        return getClass().hashCode();
    }
}
