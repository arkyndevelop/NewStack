package com.examplenewstack.newstack.book.GoogleAPI;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class GoogleBooksService {

    private static final String GOOGLE_BOOKS_API_URL = "https://www.googleapis.com/books/v1/volumes";

    private final RestTemplate restTemplate;

    public GoogleBooksService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String buscarLivroPorTitulo(String titulo) {
        String url = UriComponentsBuilder.fromHttpUrl(GOOGLE_BOOKS_API_URL)
                .queryParam("q", titulo)
                .queryParam("langRestrict", "pt")
                .build()
                .toUriString();

        return restTemplate.getForObject(url, String.class);
    }
}
