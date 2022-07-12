package com.bah.RecipeBook.services;

import com.bah.RecipeBook.commands.IngredientCommand;
import com.bah.RecipeBook.converters.IngredientToIngredientCommand;
import com.bah.RecipeBook.domain.Recipe;
import com.bah.RecipeBook.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService{

    private final IngredientToIngredientCommand ingredientCommand;
    private final RecipeRepository recipeRepository;

    public IngredientServiceImpl(IngredientToIngredientCommand ingredientCommand, RecipeRepository recipeRepository) {
        this.ingredientCommand = ingredientCommand;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(long recipeId, long ingredientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (recipeOptional.isEmpty()){
            log.error("Recipe not found. Id: " + recipeId);
        }

        Recipe recipe = recipeOptional.get();

        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredientCommand::convert).findFirst();

        if (ingredientCommandOptional.isEmpty()) {
            log.error("Ingredient Id not found. Id: " + ingredientId);
        }

        return ingredientCommandOptional.get();
    }
}
