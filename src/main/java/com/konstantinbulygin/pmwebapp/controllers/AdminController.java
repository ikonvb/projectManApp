package com.konstantinbulygin.pmwebapp.controllers;

import com.konstantinbulygin.pmwebapp.entities.UserAccount;
import com.konstantinbulygin.pmwebapp.services.UserAccountService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserAccountService userAccountService;

    public AdminController(BCryptPasswordEncoder bCryptPasswordEncoder, UserAccountService userAccountService) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userAccountService = userAccountService;
    }

    //
    @GetMapping
    public String displayUsers(Model model) {
        List<UserAccount> userAccountsList = userAccountService.findAll();
        model.addAttribute("userAccountsList", userAccountsList);
        return "admin/list-users";
    }


    //saving project to DB
    @PostMapping("/save")
    public String saveUser(Model model, @Valid UserAccount userAccount, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("message", "User not changed");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            return "admin/edit-user";
        }

        redirectAttributes.addFlashAttribute("message", "User changed");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        userAccount.setPassword(bCryptPasswordEncoder.encode(userAccount.getPassword()));

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
