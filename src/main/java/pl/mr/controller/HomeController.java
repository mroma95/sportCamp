package pl.mr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.mr.model.User;

@Controller
public class HomeController {

    @GetMapping
    public String home() {
        return "index";
    }
}
