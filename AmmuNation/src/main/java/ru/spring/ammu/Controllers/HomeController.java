package ru.spring.ammu.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.spring.ammu.Models.Employee;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping("")
    public String home()
    {


        return "/home";
    }
}
