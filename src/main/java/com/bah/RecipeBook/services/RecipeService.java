package com.bah.RecipeBook.services;

import com.bah.RecipeBook.commands.RecipeCommand;
import com.bah.RecipeBook.converters.RecipeCommandToRecipe;
import com.bah.RecipeBook.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);
}
