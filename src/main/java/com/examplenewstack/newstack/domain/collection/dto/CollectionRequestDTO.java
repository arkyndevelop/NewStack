package com.examplenewstack.newstack.domain.collection.dto;

import com.examplenewstack.newstack.domain.collection.Collection;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CollectionRequestDTO(
        @NotNull
        int totalQuantity,

        @NotNull
        int totalAvailable,

        @NotBlank
        String location
) {
    public Collection toCollection() {
        Collection collection = new Collection();

        collection.setTotal_quantity(totalQuantity);
        collection.setTotal_available(totalAvailable);
        collection.setLocation(location);

        return collection;
    }
}
