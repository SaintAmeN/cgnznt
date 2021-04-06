package com.sshpr.cgnznt.demo.controller;

import com.sshpr.cgnznt.demo.model.dto.UserRegistrationRequest;
import com.sshpr.cgnznt.demo.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping(path = "/")
@RequiredArgsConstructor
public class IndexController {

    private final String myName;
    private final AccountService accountService;

    @GetMapping("/")
    public String getIndexPage(Model model) {
        model.addAttribute("myName", myName);

        return "index";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login-form";
    }

    @GetMapping("/register")
    public String registrationForm() {
        return "registration-form";
    }

    @PostMapping("/register")
    public String register(Model model,
                           @Valid UserRegistrationRequest request,
                           BindingResult bindingResult) {
        if (!request.arePasswordsEqual()) {
            model.addAttribute("errorMessage", "Passwords do not match");
            return "registration-form";
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", bindingResult.getFieldError().getDefaultMessage());
            return "registration-form";
        }
        if (!accountService.register(request)) {
            model.addAttribute("errorMessage", "This username is already taken.");
            return "registration-form";
        }
        return "redirect:/login";
    }
}