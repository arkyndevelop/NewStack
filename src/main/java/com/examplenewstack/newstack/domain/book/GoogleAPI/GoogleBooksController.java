package com.examplenewstack.newstack.domain.book.GoogleAPI;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/google-books")
public class GoogleBooksController {

    @Autowired
    private GoogleBooksService googleBooksService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<?> buscarPorTitulo(@PathVariable String titulo) {
        try {
            String json = googleBooksService.buscarLivroPorTitulo(titulo);
            GoogleBooksResponse response = objectMapper.readValue(json, GoogleBooksResponse.class);

            if (response.getItems() == null || response.getItems().isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            GoogleBooksResponse.VolumeInfo info = response.getItems().get(0).getVolumeInfo();

            return ResponseEntity.ok().body(info);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao buscar livro: " + e.getMessage());
        }
    }
}