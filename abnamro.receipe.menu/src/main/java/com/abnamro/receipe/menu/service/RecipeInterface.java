package com.abnamro.receipe.menu.service;

import java.util.List;

import com.abnamro.receipe.menu.entity.Recipes;
import com.abnamro.receipe.menu.model.RecipeModelClass;

public interface RecipeInterface {
	
	public Recipes addRecipe(RecipeModelClass recipe);

	public List<RecipeModelClass> getallRecipes();

	public RecipeModelClass getRecipe(int id);

	public boolean deleteRecipe(int id);

	public RecipeModelClass updateRecipe(int id, RecipeModelClass recipes);

}
