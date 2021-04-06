package com.sshpr.cgnznt.demo.controller;

import com.sshpr.cgnznt.demo.model.LanguageChoice;
import com.sshpr.cgnznt.demo.model.dto.SubmissionRequest;
import com.sshpr.cgnznt.demo.service.RexTesterAPIService;
import com.sshpr.cgnznt.demo.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Author: amen
 * Date: 06/04/2021
 * Project: demo
 */
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/competition")
@PreAuthorize(value = "isAuthenticated()")
public class CompetitionController {
    private final RexTesterAPIService testerAPIService;
    private final TaskService taskService;

    @GetMapping
    public String loadForm(Model model, UsernamePasswordAuthenticationToken principal, SubmissionRequest request) {
        User account = (User) principal.getPrincipal();
        log.info("User " + account.getUsername() + " requested submission form.");
        request.setUser(account.getUsername());
        request.setLanguageChoice(LanguageChoice.Java); // default
        model.addAttribute("submissionRequest", request);
        model.addAttribute("languages", LanguageChoice.values());
        model.addAttribute("tasks", taskService.getAll());
        return "submit-form";
    }

    @PostMapping
    public String submitForm(UsernamePasswordAuthenticationToken principal, SubmissionRequest request) {
        User account = (User) principal.getPrincipal();
        log.info("User " + account.getUsername() + " submitted form.");
        if (!request.getUser().equals(account.getUsername())){
            log.warn("Logged in user does not match.");
            return "redirect:/competition";
        }
        log.info("Submitted: " + request);
        testerAPIService.submit(request);
        return "submit-form";
    }
}
