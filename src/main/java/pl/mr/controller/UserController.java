package pl.mr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.mr.model.Child;
import pl.mr.model.User;
import pl.mr.repository.UserRepository;
import pl.mr.service.UserService;

import javax.validation.Valid;

@Controller
public class UserController {
    private UserRepository userRepository;
    private UserService userService; //

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping(value = "/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @GetMapping("/loginform")
    public String login() {
        return "loginform";
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping(value = "/register")
    public String registerPost(@ModelAttribute @Valid User user, BindingResult bindingResult, Model model) {
        User userExist = userRepository.findByEmail(user.getEmail());
        if (userExist != null) {
            bindingResult.rejectValue("email", "error.user", "Jest już użytkownik o podanym adresie email");
        }
        if (bindingResult.hasErrors()) {
            return "register";
        } else {
            userRepository.save(user);
//            model.addAttribute("user", user);
            userService.registerUser(user); //
            userService.addWithDefaultRole(user);
            return "registerSuccess";
        }
    }

    @GetMapping("/logoutSuccess")
    public String logout() {
        return "logoutSuccess";
    }
}
