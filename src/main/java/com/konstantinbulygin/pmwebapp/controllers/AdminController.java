package com.konstantinbulygin.pmwebapp.controllers;

import com.konstantinbulygin.pmwebapp.entities.UserAccount;
import com.konstantinbulygin.pmwebapp.services.UserAccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserAccountService userAccountService;

    public AdminController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @GetMapping
    public String displayUsers(Model model) {
        List<UserAccount> userAccountsList = userAccountService.findAll();
        model.addAttribute("userAccountsList", userAccountsList);
        return "admin/list-users";
    }


    //saving project to DB
    @PostMapping("/save")
    public String saveUser(Model model, @Valid UserAccount userAccount, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "admin/edit-user";
        }

        userAccountService.save(userAccount);

        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String displayEditUsersForm(@PathVariable long id, Model model) {

        UserAccount userAccount = userAccountService.findByUserId(id);
        model.addAttribute("userAccount", userAccount);
        return "admin/edit-user";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable long id) {
        userAccountService.delete(id);
        return "redirect:/admin";
    }
}
