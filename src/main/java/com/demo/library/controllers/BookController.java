package com.demo.library.controllers;

import com.demo.library.models.Book;
import com.demo.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/book")
    public String bookMain(Model model) {
        Iterable<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "book-main";
    }

    @GetMapping("/book/add")
    public String bookAdd(Model model) {
        return "book-add";
    }

    @PostMapping("/book/add")
    public String bookPostAdd(@RequestParam String title,
                              @RequestParam String author,
                              @RequestParam String binding,
                              @RequestParam String isbn,
                              @RequestParam String year_of_publishing,
                              @RequestParam int circulation, Model model) {
        Book book = new Book(title, author, binding, isbn, year_of_publishing, circulation);
        bookRepository.save(book);
        return "redirect:/book";
    }

    @GetMapping("/book/{id}")
    public String bookDetails(@PathVariable(value = "id") long id, Model model) {
        if(!bookRepository.existsById(id)){
            return "redirect:/book";
        }

        Optional<Book> book = bookRepository.findById(id);
        ArrayList<Book> res = new ArrayList<>();
        book.ifPresent(res::add);
        model.addAttribute("book", res);
        return "book-details";
    }

    @GetMapping("/book/{id}/edit")
    public String bookEdit(@PathVariable(value = "id") long id, Model model) {
        if(!bookRepository.existsById(id)){
            return "redirect:/book";
        }

        Optional<Book> book = bookRepository.findById(id);
        ArrayList<Book> res = new ArrayList<>();
        book.ifPresent(res::add);
        model.addAttribute("book", res);
        return "book-edit";
    }

    @PostMapping("/book/{id}/edit")
    public String bookPostUpdate(@PathVariable(value = "id") long id,
                                 @RequestParam String title,
                                 @RequestParam String author,
                                 @RequestParam String binding,
                                 @RequestParam String isbn,
                                 @RequestParam String year_of_publishing,
                                 @RequestParam int circulation, Model model) {
        Book book = bookRepository.findById(id).orElseThrow();
        book.setTitle(title);
        book.setAuthor(author);
        book.setBinding(binding);
        book.setIsbn(isbn);
        book.setYear_of_publishing(year_of_publishing);
        book.setCirculation(circulation);
        bookRepository.save(book);

        return "redirect:/book";
    }

    @PostMapping("/book/{id}/remove")
    public String bookPostDelete(@PathVariable(value = "id") long id, Model model) {
        Book book = bookRepository.findById(id).orElseThrow();
        bookRepository.delete(book);

        return "redirect:/book";
    }
}
