package com.springboot.bookstoreDB.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
@AllArgsConstructor
public abstract class LibraryItemEntity
{
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    protected Long id;

    @NotNull
    @Column(name = "title")
    protected String title;

    @NotNull
    @Column(name = "author")
    protected String author;

    @NotNull
    @Column(name = "date")
    protected LocalDate date;

    @NotNull
    @Column(name = "page")
    protected int page;

    @NotNull
    @Column(name = "quantity")
    protected int quantity;

    @NotNull
    @Column(name = "description", length = 1500)
    protected String description;

    @NotNull
    @Column(name = "url_image", length = 1500)
    protected String urlImage;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinTable(name = "LibraryItemComments",
        joinColumns = @JoinColumn(name = "itemId", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "commentId", referencedColumnName = "commentId"))
    protected List<CommentEntity> commentList = new ArrayList<>();

    @Column(name = "Amount_Comments")
    protected int amountOfComments = 0;

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
        final LibraryItemEntity that = (LibraryItemEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode()
    {
        return getClass().hashCode();
    }

    public void addToCommentList(final CommentEntity commentEntity)
    {
        if (this.commentList == null)
        {
            this.commentList = new ArrayList<>();
        }
        this.commentList.add(commentEntity);
    }
}