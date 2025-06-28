
package com.examplenewstack.newstack.domain.book.controller.view;


import com.examplenewstack.newstack.domain.book.Book;
import com.examplenewstack.newstack.domain.book.repository.BookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/v1")
public class BookController {

    private final BookRepository livroRepository;

    public BookController(BookRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    // Mostra a tela de cadastro (view)
    @GetMapping("/book")
    public String mostrarFormularioCadastro(Model model) {
        model.addAttribute("book", new Book());
        return "registerBook"; // <-- nome do arquivo HTML sem extensão
    }

    // Recebe o POST do formulário
    @PostMapping("/books/register")
    @ResponseBody
    public ResponseEntity<String> cadastrar(@ModelAttribute Book book) {
        try {
            livroRepository.save(book);
            return ResponseEntity.ok("Livro salvo com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao salvar livro: " + e.getMessage());
        }
    }
}
