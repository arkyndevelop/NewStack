package com.examplenewstack.newstack.collection.dto;

public record CollectionResponseDTO(
    int totalQuantity,
    int totalAvailable,
    String location
){
}
