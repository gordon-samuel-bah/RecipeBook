package com.bah.RecipeBook.repositories;

import com.bah.RecipeBook.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
