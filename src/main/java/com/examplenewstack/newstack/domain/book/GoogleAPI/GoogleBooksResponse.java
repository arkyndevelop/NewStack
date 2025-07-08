package com.examplenewstack.newstack.domain.book.GoogleAPI;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleBooksResponse {
    private List<Item> items;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Item {
        private VolumeInfo volumeInfo;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class VolumeInfo {
        private String title;
        private List<String> authors;
        private String description;
        private String publisher;
        private String publishedDate;
        private List<String> categories;
        private ImageLinks imageLinks;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ImageLinks {
        private String thumbnail;
    }
}