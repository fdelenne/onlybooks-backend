package com.springboot.bookstoreDB.entity;

import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "comments")
public class CommentEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "commentIdGenerator")
    @Column(name = "commentId")
    private Long id;

    private String author;

    private String comment;

    private LocalDate date;

    public void setDate(final LocalDate date)
    {
        this.date = LocalDate.now();
    }

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
        final CommentEntity that = (CommentEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode()
    {
        return getClass().hashCode();
    }
}
