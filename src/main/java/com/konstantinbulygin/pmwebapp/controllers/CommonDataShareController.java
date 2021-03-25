package com.konstantinbulygin.pmwebapp.controllers;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;


import java.security.Principal;

@ControllerAdvice
public class CommonDataShareController {

    @ModelAttribute
    public void shareData(Principal principal, Model model) {
        if (principal != null) {
            model.addAttribute("principal", principal.getName());
        }
    }
}

