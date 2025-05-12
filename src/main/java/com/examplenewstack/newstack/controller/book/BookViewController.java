package com.examplenewstack.newstack.controller.book;

import com.examplenewstack.newstack.entity.dto.bookdto.BookDTO;
import com.examplenewstack.newstack.entity.librarie.book.Book;
import com.examplenewstack.newstack.repository.BookRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/books")
public class BookViewController {

    @GetMapping("/register")
    public ModelAndView registerScreenBook(){
        return new ModelAndView("admMaster");
    }
}
