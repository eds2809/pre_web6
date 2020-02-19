package ru.eds2809.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.eds2809.intarfaces.UserRepository;
import ru.eds2809.model.User;


@Controller
public class MainController {

    private final UserRepository userRepository;

    public MainController(@Qualifier("hibernateEntityManagerRepository") UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String getIndexPage(Model model, Authentication authentication) {
        User user = userRepository.getUserByLogin(authentication.getName());
        model.addAttribute("user",user);
        return "userHome";
    }
}
