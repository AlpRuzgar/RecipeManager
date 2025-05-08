package com.example.recipemanager.controller;

import com.example.recipemanager.model.Recipe;
import com.example.recipemanager.service.RecipeService;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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

	@GetMapping("/{id}")
	public String viewRecipeDetails(@PathVariable Long id, Model model) {
		Recipe recipe = service.findById(id);
		model.addAttribute("recipe", recipe);
		return "details";
	}

	@GetMapping("/search")
	public String searchRecipes(@RequestParam("keyword") String keyword, Model model) {
		System.out.println("Search called with keyword: " + keyword);
		List<Recipe> results = service.searchByName(keyword);
		model.addAttribute("recipes", results);
		return "search";
	}

	@GetMapping("/stats")
	public String showStats(Model model) {
		model.addAttribute("total", service.countAll());
		model.addAttribute("averageTime", service.getAverageCookingTime());
		model.addAttribute("difficultyCounts", service.getCountByDifficulty());
		return "stats";
	}

	@PostMapping
	public String save(@Valid @ModelAttribute Recipe recipe, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("recipe", recipe);
			return "form";
		}
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
