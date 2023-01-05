package com.springboot.bookstoreDB.dto;

import java.time.LocalDate;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@NotNull
@Validated
public class CommentDTO
{
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String author;

    private String comment;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDate date;
}
