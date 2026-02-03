package es.uma.taw.sceneit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Controlador_Base {

    @GetMapping("/")
    public String doIndex(){
        return "redirect:/movie/";
    }
}
