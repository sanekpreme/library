package com.demo.library.controller;

import com.demo.library.model.Author;
import com.demo.library.model.User;
import com.demo.library.repository.AuthorRepository;
import com.demo.library.service.AuthorService;
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
public class AuthorController {

    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository, AuthorService authorService) {
        this.authorRepository = authorRepository;
        this.authorService = authorService;
    }

    final AuthorService authorService;
    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/author")
    public String getAuthor(Model model) {
        model.addAttribute("authors", authorService.getAllAuthor());
        return "authorMain";
    }

    @GetMapping("/author/add")
    public String getAuthorAdd(Model model) {
        model.addAttribute("author", new Author());
        return "authorAdd";
    }

//    @PostMapping("/author/add")
//    public String authorPostAdd(@ModelAttribute("author") Author author,
//                                @RequestParam("file") MultipartFile file,
//                                @AuthenticationPrincipal User user,
//                                Model model) throws IOException {
//        if (file != null && !file.getOriginalFilename().isEmpty()) {
//            File uploadDir = new File(uploadPath);
//
//            if (!uploadDir.exists()) {
//                uploadDir.mkdir();
//            }
//
//            String uuidFile = UUID.randomUUID().toString();
//            String resultFileName = uuidFile + "." + file.getOriginalFilename();
//            file.transferTo(new File(uploadPath + "/" + resultFileName));
//            author.setFilename(resultFileName);
//
//        }
//        authorService.addAuthor(author);
//        return "redirect:/author";
//    }

    @GetMapping("/author/{authorId}")
    public String authorPostDetails(@PathVariable(value = "authorId") Integer authorId, Model model) {
        if (!authorRepository.existsById(authorId)) {
            return "redirect:/author";
        }

        Optional<Author> author = authorRepository.findById(authorId);
        List<Author> res = new ArrayList<>();
        author.ifPresent(res::add);
        model.addAttribute("author", res);
        return "authorDetails";
    }

    @GetMapping("/author/{authorId}/edit")
    public String authorEdit(@PathVariable(value = "authorId") Integer authorId, Model model) {
        if (!authorRepository.existsById(authorId)) {
            return "redirect:/author";
        }

        Optional<Author> author = authorRepository.findById(authorId);
        List<Author> res = new ArrayList<>();
        author.ifPresent(res::add);
        model.addAttribute("author", res);
        return "authorEdit";
    }

    @PostMapping("/author/{authorId}/remove")
    public String authorPostDelete(@PathVariable(value = "authorId") Integer authorId, Model model) {
        Author author = authorRepository.findById(authorId).orElseThrow();
        authorRepository.delete(author);

        return "redirect:/author";
    }

//    @PostMapping("/author/{authorId}/edit")
//    public String authorPostUpdates(@PathVariable(value = "authorId") Integer authorId,
//                                    @RequestParam String authorCountry,
//                                    @RequestParam String authorDetails,
//                                    @RequestParam String authorName,
//                                    @RequestParam("file") MultipartFile file,
//                                    Model model) throws IOException {
//        Author author = authorRepository.findById(authorId).orElseThrow(); //.orElseThrow()
//        if (file != null && !file.getOriginalFilename().isEmpty()) {
//            File uploadDir = new File(uploadPath);
//
//            if (!uploadDir.exists()) {
//                uploadDir.mkdir();
//            }
//
//            String uuidFile = UUID.randomUUID().toString();
//            String resultFileName = uuidFile + "." + file.getOriginalFilename();
//            file.transferTo(new File(uploadPath + "/" + resultFileName));
//            author.setFilename(resultFileName);
//
//        }
//        author.setAuthorCountry(authorCountry);
//        author.setAuthorDetails(authorDetails);
//        author.setAuthorName(authorName);
//
//        authorRepository.save(author);
//
//        return "redirect:/author";
//    }

}
