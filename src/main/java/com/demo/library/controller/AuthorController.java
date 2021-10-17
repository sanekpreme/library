package com.demo.library.controller;


import com.demo.library.model.Author;
import com.demo.library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import java.util.Optional;
import java.util.UUID;

@Controller
public class AuthorController {

   // @Autowired
    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/author")
    public String authorMain(Model model){
        Iterable<Author> authors = authorRepository.findAll();
        model.addAttribute("authors", authors);
        return "authorMain";
    }

    @GetMapping("/author/add")
    public String authorAdd(Model model) {
        return "authorAdd";
    }

    @PostMapping("/author/add")
    public String authorPostAdd(@RequestParam String AuthorName,
                                @RequestParam String AuthorCountry,
                                @RequestParam String AuthorDetails,
                                @RequestParam("file") MultipartFile file, Model model) throws IOException {
        Author author = new Author(AuthorName, AuthorCountry, AuthorDetails);

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()){
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFileName));

            author.setFilename(resultFileName);


        }
        authorRepository.save(author);
        return "redirect:/author";
    }

    @GetMapping("/author/{id}")
    public String authorDetails(@PathVariable(value = "id") long id, Model model) {
        if(!authorRepository.existsById(id)){
            return "redirect:/author";
        }

        Optional<Author> author = authorRepository.findById(id);
        ArrayList<Author> res = new ArrayList<>();
        author.ifPresent(res::add);
        model.addAttribute("author", res);
        return "authorDetails";
    }

    @GetMapping("/author/{id}/edit")
    public String authorEdit(@PathVariable(value = "id") long id, Model model) {
        if(!authorRepository.existsById(id)){
            return "redirect:/author";
        }

        Optional<Author> author = authorRepository.findById(id);
        ArrayList<Author> res = new ArrayList<>();
        author.ifPresent(res::add);
        model.addAttribute("author", res);
        return "authorEdit";
    }

    @PostMapping("/author/{id}/edit")
    public String authorPostUpdate(@PathVariable(value = "id") long id,
                                 @RequestParam String AuthorCountry,
                                 @RequestParam String AuthorDetails,
                                 @RequestParam String AuthorName,
                                 @RequestParam("file") MultipartFile file,
                                 Model model) throws IOException {
        Author author = authorRepository.findById(id).orElseThrow();
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()){
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFileName));

            author.setFilename(resultFileName);

        }
        author.setAuthorCountry(AuthorCountry);
        author.setAuthorDetails(AuthorDetails);
        author.setAuthorName(AuthorName);
        authorRepository.save(author);

        return "redirect:/author";
    }

    @PostMapping("/author/{id}/remove")
    public String authorPostDelete(@PathVariable(value = "id") long id, Model model) {
        Author author = authorRepository.findById(id).orElseThrow();
        authorRepository.delete(author);

        return "redirect:/author";
    }
}
