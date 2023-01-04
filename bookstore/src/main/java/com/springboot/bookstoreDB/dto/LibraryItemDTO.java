package com.springboot.bookstoreDB.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Getter
@Component
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "itemType")
@JsonSubTypes({ @JsonSubTypes.Type(value = BookDTO.class, name = "BOOK"), @JsonSubTypes.Type(value = LetterDTO.class, name = "LETTER"),
    @JsonSubTypes.Type(value = MagazineDTO.class, name = "MAGAZINE"), @JsonSubTypes.Type(value = NewspaperDTO.class, name = "NEWSPAPER") })
public abstract class LibraryItemDTO
{
    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    protected Long id;

    @NotNull
    protected String title;

    @NotNull
    protected String author;

    @NotNull
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    protected LocalDate date;

    @NotNull
    protected int page;

    @NotNull
    protected int quantity;

    @NotNull
    protected String description;

    @NotNull
    protected String urlImage;

    //    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonIgnore
    protected List<CommentDTO> commentList = new ArrayList<>();

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    protected int amountOfComments = 0;
}