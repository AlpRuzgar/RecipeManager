package com.example.recipemanager.repository;

import com.example.recipemanager.model.Recipe;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
	List<Recipe> findByNameContainingIgnoreCase(String name);

	@Query("SELECT AVG(r.cookingTime) FROM Recipe r")
	Double getAverageCookingTime();

}
