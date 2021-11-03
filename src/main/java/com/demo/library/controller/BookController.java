package com.demo.library.controller;

import com.demo.library.DTO.BookDTO;
import com.demo.library.model.Author;
import com.demo.library.model.Book;
import com.demo.library.model.User;
import com.demo.library.repository.BookRepository;
import com.demo.library.service.AuthorService;
import com.demo.library.service.BookService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository, AuthorService authorService, BookService bookService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    final AuthorService authorService;

    final BookService bookService;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/book")
    public String books(Model model) {
        model.addAttribute("books", bookService.getAllBook());
        return "bookMain";
    }

    @GetMapping("/book/add")
    public String bookAdd(Model model) {
        model.addAttribute("bookDTO", new BookDTO());
        model.addAttribute("authors", authorService.getAllAuthor());
        return "bookAdd";
    }

    @PostMapping("/book/add")
    public String bookPostAdd(@ModelAttribute("bookDTO") BookDTO bookDTO,
                              @RequestParam("file") MultipartFile file,
                              @AuthenticationPrincipal User user,
                              Model model) throws IOException {
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setCirculation(bookDTO.getCirculation());
        book.setAuthor(authorService.getAuthorById(bookDTO.getAuthorId()).get());
        book.setYearOfPublishing(bookDTO.getYearOfPublishing());
        book.setBinding(bookDTO.getBinding());
        book.setIsbn(bookDTO.getIsbn());
        book.setTitle(bookDTO.getTitle());
        book.setFilename(bookDTO.getFilename());
        book.setDescription(bookDTO.getDescription());
        book.setPrice(bookDTO.getPrice());
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFileName));
            book.setFilename(resultFileName);

        }
        bookService.addBook(book);
        return "redirect:/book";
    }

    @GetMapping("/book/{bookId}/edit")
    public String bookEdit(@PathVariable(value = "bookId") Integer bookId, Model model) {
        if (!bookRepository.existsById(bookId)) {
            return "redirect:/book";
        }

        Optional<Book> book = bookRepository.findById(bookId);
        List<Book> res = new ArrayList<>();
        book.ifPresent(res::add);
        model.addAttribute("book", res);
        return "bookEdit";
    }

    @GetMapping("/book/{bookId}")
    public String bookDetails(@PathVariable(value = "bookId") Integer bookId, Model model) {
        if (!bookRepository.existsById(bookId)) {
            return "redirect:/book";
        }

        Optional<Book> book = bookRepository.findById(bookId);
        List<Book> res = new ArrayList<>();
        book.ifPresent(res::add);
        model.addAttribute("book", res);
        return "bookDetails";
    }

    @PostMapping("/book/{bookId}/remove")
    public String bookPostDelete(@PathVariable(value = "bookId") Integer bookId, Model model) {
        Book book = bookRepository.findById(bookId).orElseThrow();
        bookRepository.delete(book);

        return "redirect:/book";
    }

    @PostMapping("/book/{bookId}/edit")
    public String bookPostUpdate(@PathVariable(value = "bookId") Integer bookId,
                                 @RequestParam String title,
                                 @RequestParam String binding,
                                 @RequestParam String isbn,
                                 @RequestParam String yearOfPublishing,
                                 @RequestParam String description,
                                 @RequestParam int circulation,
                                 @RequestParam double price,
                                 @RequestParam("file") MultipartFile file,
                                 Model model) throws IOException {
        Book book = bookRepository.findById(bookId).orElseThrow();
        BookDTO bookDTO = new BookDTO();
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFileName));
            book.setFilename(resultFileName);
        }

        book.setTitle(title);
        book.setDescription(description);
        book.setBinding(binding);
        book.setIsbn(isbn);
        book.setYearOfPublishing(yearOfPublishing);
        book.setCirculation(circulation);
        bookDTO.setAuthorId((book.getAuthor().getId()));
        book.setPrice(price);

        bookRepository.save(book);

        return "redirect:/book";
    }

}
