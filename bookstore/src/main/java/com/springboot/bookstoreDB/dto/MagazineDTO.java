package com.springboot.bookstoreDB.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class MagazineDTO extends LibraryItemDTO
{
    @NotNull
    private int articles;

    @NotNull
    private String category;
}
