package com.springboot.bookstoreDB.models;

import java.util.Optional;

import com.springboot.bookstoreDB.controllers.LibraryItemController;
import com.springboot.bookstoreDB.dto.LibraryItemDTO;
import com.springboot.bookstoreDB.utils.ItemTypes;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class LibraryItemModelAssembler implements RepresentationModelAssembler<LibraryItemDTO, EntityModel<LibraryItemDTO>>
{
    @Override
    public EntityModel<LibraryItemDTO> toModel(final LibraryItemDTO libraryItemDTO)
    {

        return EntityModel.of(libraryItemDTO,
            linkTo(methodOn(LibraryItemController.class).getItem(libraryItemDTO.getId(),
                Optional.ofNullable(ItemTypes.getItemType(libraryItemDTO)))).withSelfRel(),
            linkTo(methodOn(LibraryItemController.class).getAllShelves()).withRel("item"));
    }
}
