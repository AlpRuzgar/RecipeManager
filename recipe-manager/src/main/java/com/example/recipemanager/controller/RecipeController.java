package com.example.recipemanager.controller;

import com.example.recipemanager.model.Recipe;
import com.example.recipemanager.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/recipes")
public class RecipeController {
    private final RecipeService service;

    public RecipeController(RecipeService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("recipes", service.findAll());
        return "list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("recipe", new Recipe());
        return "form";
    }

    @PostMapping
    public String save(@ModelAttribute Recipe recipe) {
        recipe.setDateAdded(LocalDate.now());
        service.save(recipe);
        return "redirect:/recipes";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("recipe", service.findById(id));
        return "form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/recipes";
    }
}
