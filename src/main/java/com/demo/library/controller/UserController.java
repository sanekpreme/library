package com.demo.library.controller;

import com.demo.library.model.Role;
import com.demo.library.model.User;
import com.demo.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class UserController {
    //@Autowired
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "userList";
    }

    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam(name = "roles[]", required = false) String[] roles,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user) {
        user.setUsername(username);
        user.getRoles().clear();
        if (roles != null) {
            Arrays.stream(roles).forEach(r -> user.getRoles().add(Role.valueOf(r)));
        }
        userRepository.save(user);
        return "redirect:/user";

    }
}
