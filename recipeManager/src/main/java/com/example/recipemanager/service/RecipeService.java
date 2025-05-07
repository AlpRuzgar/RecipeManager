package com.example.recipemanager.service;

import com.example.recipemanager.model.Recipe;
import com.example.recipemanager.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {
    private final RecipeRepository repo;

    public RecipeService(RecipeRepository repo) {
        this.repo = repo;
    }

    public List<Recipe> findAll() { return repo.findAll(); }
    public Recipe findById(Long id) { return repo.findById(id).orElseThrow(); }
    public Recipe save(Recipe recipe) { return repo.save(recipe); }
    public void deleteById(Long id) { repo.deleteById(id); }
}
