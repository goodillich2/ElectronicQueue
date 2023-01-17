package com.example.springmarket.controller2;


import com.example.springmarket.model.Person;
import com.example.springmarket.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class TestController {



    @Autowired
    PersonRepository personRepository;

    @GetMapping("/")
    public String getMainPage(Model model){
        return "main";
    }

}
