package com.example.recipemanager.service;

import com.example.recipemanager.model.Recipe;
import com.example.recipemanager.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RecipeService {
	private final RecipeRepository repo;

	public RecipeService(RecipeRepository repo) {
		this.repo = repo;
	}

	public Recipe getRecipeById(Long id) {
		return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid recipe Id:" + id));
	}

	public List<Recipe> searchByName(String keyword) {
		return repo.findByNameContainingIgnoreCase(keyword);
	}

	public long countAll() {
		return repo.count();
	}

	public double getAverageCookingTime() {
		Double avg = repo.getAverageCookingTime(); // may be null
		return avg == null ? 0 : avg;
	}

	public Map<String, Long> getCountByDifficulty() {
		// when the list is empty, return an empty map instead of null
		return repo.findAll().stream().collect(Collectors.groupingBy(Recipe::getDifficulty, Collectors.counting()));
	}

	public List<Recipe> findAll() {
		return repo.findAll();
	}

	public Recipe findById(Long id) {
		return repo.findById(id).orElseThrow();
	}

	public Recipe save(Recipe recipe) {
		return repo.save(recipe);
	}

	public void deleteById(Long id) {
		repo.deleteById(id);
	}
}
