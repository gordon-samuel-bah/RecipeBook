package com.bah.RecipeBook.services;

import com.bah.RecipeBook.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();
}
