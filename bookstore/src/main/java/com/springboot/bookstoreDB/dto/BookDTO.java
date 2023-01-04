package com.springboot.bookstoreDB.dto;

import java.util.Objects;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

@Setter
@Getter
@ToString(callSuper = true)
@Data
public class BookDTO extends LibraryItemDTO
{
    @NotNull
    private Long ISBN;

    @NotNull
    private String publisher;

    @NotNull
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
        final BookDTO bookDTO = (BookDTO) o;
        return id != null && Objects.equals(id, bookDTO.id);
    }

    @Override
    public int hashCode()
    {
        return getClass().hashCode();
    }
}
