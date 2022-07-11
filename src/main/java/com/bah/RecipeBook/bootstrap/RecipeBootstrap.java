package com.bah.RecipeBook.bootstrap;

import com.bah.RecipeBook.domain.*;
import com.bah.RecipeBook.repositories.CategoryRepository;
import com.bah.RecipeBook.repositories.RecipeRepository;
import com.bah.RecipeBook.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        recipeRepository.saveAll(getRecipes());
        log.debug("Loading Boostrap Data");
    }

    private List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<>(2);

        Optional<UnitOfMeasure> eachUnitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Each");

        if (eachUnitOfMeasureOptional.isEmpty()) {
            throw new RuntimeException("UOM Each not found");
        }

        Optional<UnitOfMeasure> tablespoonUnitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Tablespoon");

        if (tablespoonUnitOfMeasureOptional.isEmpty()) {
            throw new RuntimeException("UOM Tablespoon not found");
        }

        Optional<UnitOfMeasure> teaspoonUnitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        if (teaspoonUnitOfMeasureOptional.isEmpty()) {
            throw new RuntimeException("UOM Teaspoon not found");
        }

        Optional<UnitOfMeasure> dashUnitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Dash");

        if (dashUnitOfMeasureOptional.isEmpty()) {
            throw new RuntimeException("UOM Dash not found");
        }

        Optional<UnitOfMeasure> pintUnitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Pint");

        if (pintUnitOfMeasureOptional.isEmpty()) {
            throw new RuntimeException("UOM Pint not found");
        }

        Optional<UnitOfMeasure> cupUnitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Cup");

        if (cupUnitOfMeasureOptional.isEmpty()) {
            throw new RuntimeException("UOM Cup not found");
        }

        UnitOfMeasure eachUom = eachUnitOfMeasureOptional.get();
        UnitOfMeasure tablespoonUom = tablespoonUnitOfMeasureOptional.get();
        UnitOfMeasure teaspoonUom = teaspoonUnitOfMeasureOptional.get();
        UnitOfMeasure dashUom = dashUnitOfMeasureOptional.get();
        UnitOfMeasure pintUom = pintUnitOfMeasureOptional.get();
        UnitOfMeasure cupUom = cupUnitOfMeasureOptional.get();

        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");

        if (americanCategoryOptional.isEmpty()) {
            throw new RuntimeException("Expected category not found");
        }

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");

        if (mexicanCategoryOptional.isEmpty()) {
            throw new RuntimeException("Expected category not found");
        }

        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();

        //Guacamole recipe
        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(0);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections("I am way too lazy for that");
        Notes guacNotes = new Notes();
        guacNotes.setRecipe(guacRecipe);
        guacNotes.setRecipeNotes("Also too lazy, but these are notes - Guac");
        guacRecipe.setNotes(guacNotes);

        guacRecipe.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), eachUom));
        guacRecipe.addIngredient(new Ingredient("Kosher Salt", new BigDecimal(".5"), teaspoonUom));
        guacRecipe.addIngredient(new Ingredient("Fresh lime or lemon juice", new BigDecimal(2), tablespoonUom));
        guacRecipe.addIngredient(new Ingredient("Minced red onion", new BigDecimal(2), tablespoonUom));
        guacRecipe.addIngredient(new Ingredient("serrano chiles", new BigDecimal(2), eachUom));
        guacRecipe.addIngredient(new Ingredient("Cilantro", new BigDecimal(2), tablespoonUom));
        guacRecipe.addIngredient(new Ingredient("black pepper", new BigDecimal(2), dashUom));
        guacRecipe.addIngredient(new Ingredient("Tomato", new BigDecimal(".5"), pintUom));

        guacRecipe.getCategories().add(americanCategory);
        guacRecipe.getCategories().add(mexicanCategory);

        //Add to return list
        recipes.add(guacRecipe);

        //Chicken Taco Recipe
        Recipe tacoRecipe = new Recipe();
        tacoRecipe.setDescription("Spicy Grilled Chicken Taco");
        tacoRecipe.setCookTime(9);
        tacoRecipe.setPrepTime(20);
        tacoRecipe.setDifficulty(Difficulty.MODERATE);
        tacoRecipe.setDirections("Zero chance taco directions");
        Notes tacoNote = new Notes();
        tacoNote.setRecipe(tacoRecipe);
        tacoNote.setRecipeNotes("Also zero chance taco notes");
        tacoRecipe.setNotes(tacoNote);

        tacoRecipe.addIngredient(new Ingredient("Chili Powder", new BigDecimal(2), tablespoonUom));
        tacoRecipe.addIngredient(new Ingredient("Dried Oregano", new BigDecimal(1), teaspoonUom));
        tacoRecipe.addIngredient(new Ingredient("Dried Cumin", new BigDecimal(1), teaspoonUom));
        tacoRecipe.addIngredient(new Ingredient("Sugar", new BigDecimal(1), teaspoonUom));
        tacoRecipe.addIngredient(new Ingredient("Salt", new BigDecimal(".5"), teaspoonUom));
        tacoRecipe.addIngredient(new Ingredient("Chopped Garlic", new BigDecimal(1), eachUom));
        tacoRecipe.addIngredient(new Ingredient("Orange Zest", new BigDecimal(1), tablespoonUom));
        tacoRecipe.addIngredient(new Ingredient("Orange Juice", new BigDecimal(3), tablespoonUom));
        tacoRecipe.addIngredient(new Ingredient("Olive Oil", new BigDecimal(2), tablespoonUom));
        tacoRecipe.addIngredient(new Ingredient("Chicken Thighs", new BigDecimal(4), eachUom));
        tacoRecipe.addIngredient(new Ingredient("Tortillas", new BigDecimal(8), eachUom));
        tacoRecipe.addIngredient(new Ingredient("Arugula", new BigDecimal(3), cupUom));
        tacoRecipe.addIngredient(new Ingredient("Sliced Avocado", new BigDecimal(2), eachUom));
        tacoRecipe.addIngredient(new Ingredient("Radishes", new BigDecimal(4), eachUom));
        tacoRecipe.addIngredient(new Ingredient("Tomatoes", new BigDecimal(".5"), pintUom));
        tacoRecipe.addIngredient(new Ingredient("Red Onion", new BigDecimal(".25"), eachUom));
        tacoRecipe.addIngredient(new Ingredient("Cilantro", new BigDecimal(4), eachUom));
        tacoRecipe.addIngredient(new Ingredient("Sour Cream", new BigDecimal(4), cupUom));
        tacoRecipe.addIngredient(new Ingredient("Lime Wedges", new BigDecimal(4), eachUom));

        tacoRecipe.getCategories().add(americanCategory);
        tacoRecipe.getCategories().add(mexicanCategory);

        recipes.add(tacoRecipe);
        return recipes;
    }

}
