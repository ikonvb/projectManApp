package com.konstantinbulygin.pmwebapp.controllers;

import com.konstantinbulygin.pmwebapp.entities.UserAccount;
import com.konstantinbulygin.pmwebapp.services.UserAccountService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SecurityController {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserAccountService userAccountService;

    public SecurityController(BCryptPasswordEncoder bCryptPasswordEncoder, UserAccountService userAccountService) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userAccountService = userAccountService;
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userAccount", new UserAccount());
        return "security/register";
    }

    @PostMapping("/register/save")
    public String saveUser(Model model, UserAccount userAccount) {
        userAccount.setPassword(bCryptPasswordEncoder.encode(userAccount.getPassword()));
        userAccountService.save(userAccount);
        return "redirect:/";
    }
}
