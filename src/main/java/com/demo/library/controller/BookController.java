package com.demo.library.controller;

import com.demo.library.model.Author;
import com.demo.library.model.Book;
import com.demo.library.model.User;
import com.demo.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class BookController {

    //@Autowired
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/book")
    public String bookMain(Model model) {
        Iterable<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "bookMain";
    }

    @GetMapping("/book/add")
    public String bookAdd(Model model) {
        return "bookAdd";
    }

    @PostMapping("/book/add")
    public String bookPostAdd(@RequestParam String title,
                              @RequestParam String author,
                              @RequestParam String binding,
                              @RequestParam String isbn,
                              @RequestParam String yearOfPublishing,
                              @RequestParam int circulation,
                              @RequestParam("file") MultipartFile file,
                              @AuthenticationPrincipal User user,
                              Model model) throws IOException {
        Book book = new Book(title, author, binding, isbn, yearOfPublishing, circulation, user);

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()){
                uploadDir.mkdir();
            }

                String uuidFile = UUID.randomUUID().toString();
                String resultFileName = uuidFile + "." + file.getOriginalFilename();

                file.transferTo(new File(uploadPath + "/" + resultFileName));

            book.setFilename(resultFileName);


        }

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
        return "bookDetails";
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
        return "bookEdit";
    }

    @PostMapping("/book/{id}/edit")
    public String bookPostUpdate(@PathVariable(value = "id") long id,
                                 @RequestParam String title,
                                 @RequestParam String author,
                                 @RequestParam String binding,
                                 @RequestParam String isbn,
                                 @RequestParam String yearOfPublishing,
                                 @RequestParam int circulation, Model model) {
        Book book = bookRepository.findById(id).orElseThrow();
        book.setTitle(title);
        book.setAuthor(author);
        book.setBinding(binding);
        book.setIsbn(isbn);
        book.setYearOfPublishing(yearOfPublishing);
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

//    @PostMapping("/filter")
//    public String filter(@RequestParam String filter, Model model){
//        List<Book> book = bookRepository.findBookByTitle(filter);
//
//        model.addAttribute("book", book);
//
//        return "redirect:/book";
//    }
}
