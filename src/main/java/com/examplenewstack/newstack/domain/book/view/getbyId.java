package com.examplenewstack.newstack.domain.book.view;



import com.examplenewstack.newstack.domain.book.service.BookCrudService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/books")
public class getbyId {

    private final BookCrudService bookCrudService;

    public getbyId(BookCrudService bookCrudService) {
        this.bookCrudService = bookCrudService;
    }

    // Método atualizado para exibir os detalhes de um livro com base no DTO BookResponseViewDetails
    @GetMapping("/details/{id}")
    public ModelAndView mostrarDetalhesLivro(@PathVariable int id) {
        var livro = bookCrudService.findViewDetailsById(id);
        System.out.println("Autor do livro: " + livro.author());
        ModelAndView mv = new ModelAndView("book-details");
        mv.addObject("livro", livro);
        return mv;
    }
}
