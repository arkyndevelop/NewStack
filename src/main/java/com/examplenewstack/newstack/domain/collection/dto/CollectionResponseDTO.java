package com.examplenewstack.newstack.domain.collection.dto;

public record CollectionResponseDTO(
    int totalQuantity,
    int totalAvailable,
    String location
){
}
