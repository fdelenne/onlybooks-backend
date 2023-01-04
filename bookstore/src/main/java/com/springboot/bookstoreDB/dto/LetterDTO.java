package com.springboot.bookstoreDB.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class LetterDTO extends LibraryItemDTO
{
    @NotNull
    private String receiver;

    @NotNull
    private String origin;

    @NotNull
    private String destination;
}
