package com.example.springkadaiform2.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.example.springkadaiform2.form.ContactForm;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class ContactFormController {

    @GetMapping("/form")
    public String showForm(Model model, HttpServletRequest request) {
        // フラッシュスコープから値を取得（リダイレクト後）
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        if (inputFlashMap != null) {
            model.addAttribute("contactForm", inputFlashMap.get("contactForm"));
            model.addAttribute("org.springframework.validation.BindingResult.contactForm",
                               inputFlashMap.get("org.springframework.validation.BindingResult.contactForm"));
        } else {
            model.addAttribute("contactForm", new ContactForm());
        }
        return "contactFormView";
    }

    @PostMapping("/confirm")
    public String confirm(@Valid @ModelAttribute ContactForm contactForm,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes,
                          Model model) {

        if (bindingResult.hasErrors()) {
            // エラー情報とフォームの値をリダイレクト用に保存
            redirectAttributes.addFlashAttribute("contactForm", contactForm);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.contactForm", bindingResult);
            return "redirect:/form";
        }

        model.addAttribute("contactForm", contactForm);
        return "confirmView";
    }
}
