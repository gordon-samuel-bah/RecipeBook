package com.bah.RecipeBook.services;

import com.bah.RecipeBook.commands.UnitOfMeasureCommand;
import com.bah.RecipeBook.domain.UnitOfMeasure;

import java.util.Set;

public interface UnitOfMeasureService {
    Set<UnitOfMeasureCommand> listAllUoms();
}
