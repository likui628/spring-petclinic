package spring.petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.petclinic.services.VetService;

@RequestMapping("/vets")
@Controller
public class VetController {

    private VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String listVets(Model model) {
        System.out.println(vetService.findAll());
        model.addAttribute("vets",vetService.findAll());
        return "vets/index";
    }
}
