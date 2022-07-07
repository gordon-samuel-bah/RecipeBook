package com.bah.RecipeBook.repositories;

import com.bah.RecipeBook.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
