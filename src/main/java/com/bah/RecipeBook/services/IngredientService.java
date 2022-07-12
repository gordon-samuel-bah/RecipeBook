package com.bah.RecipeBook.services;

import com.bah.RecipeBook.commands.IngredientCommand;

public interface IngredientService {
    IngredientCommand findByRecipeIdAndIngredientId(long recipeId, long ingredientId);
}
