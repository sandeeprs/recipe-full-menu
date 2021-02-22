package com.abnamro.receipe.menu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abnamro.receipe.menu.controller.UtilityClass;
import com.abnamro.receipe.menu.entity.Recipes;
import com.abnamro.receipe.menu.model.RecipeModelClass;
import com.abnamro.receipe.menu.repository.RecipesRepository;

@Service
public class RecipeImplementation implements RecipeInterface {

	@Autowired
	private RecipesRepository resRepo;
	
	UtilityClass uc = new UtilityClass();

	/* Method persists data into the table.
	 * Takes the input from user , maipulates it and persist the data
	 * in the table
	 */
	@Override
	@Transactional
	public Recipes addRecipe(RecipeModelClass recipe) {

		Recipes receipePersistObj = uc.demarshallingMethod(recipe);		
		receipePersistObj.setDateCreated(uc.calculateDate(new Date()));

		receipePersistObj = resRepo.save(receipePersistObj);

		return receipePersistObj;
	}

	/* Method to get all the data*(recipes) from the table 
	 * Implicitly it uses findAll method of JPARepository
	*/
	@Override
	@Transactional
	public List<RecipeModelClass> getallRecipes() {
		List<Recipes> recipeList= null;
		recipeList= resRepo.findAll();

		List<RecipeModelClass> listAllRecipes = null;

		if(!recipeList.isEmpty()) {
			listAllRecipes = new ArrayList<>();

			for(Recipes index : recipeList) {
				listAllRecipes.add(uc.marshallingMethod(index));
			}
		}

		return listAllRecipes;

	}

	/* Method to get single recipe from the table
	 * Implicitly it uses findById method of JPARepository 
	*/
	@Override
	@Transactional
	public RecipeModelClass getRecipe(int id) {

		Optional<Recipes> recipe = resRepo.findById(id);
		RecipeModelClass recipePojo = null;
		Recipes rec = null;

		if(recipe.isPresent()) {
			rec = recipe.get();
			recipePojo = uc.marshallingMethod(rec);
		}
		return recipePojo;
	}

	/* Method to get delete a recipe from the table based on the id
	 * Implicitly it uses deleteById method of JPARepository 
	*/
	@Override
	@Transactional
	public boolean deleteRecipe(int id) {
		boolean returnValue = false;
		Optional<Recipes> recipe = resRepo.findById(id);

		if(recipe.isPresent()) {
			resRepo.deleteById(id);
			returnValue = true;
			
		}		
		return returnValue;

	}

	/* Method to get update a recipe from the table based on the id
	 * Implicitly it uses save method of JPARepository.
	 * Save() is used for both insertion and updation. 
	 * JpaRepository differentiates based on the id.
	 * During insertion id is 0 while during updation id >0
	*/
	@Override
	@Transactional
	public RecipeModelClass updateRecipe(int id, RecipeModelClass recipes) {

		Recipes updatedRecipe = uc.demarshallingMethod(recipes);		
		updatedRecipe.setId(id);
		updatedRecipe.setDateCreated(uc.calculateDate(new Date()));

		Recipes recipeUnmarsh = resRepo.save(updatedRecipe);
		recipes = uc.marshallingMethod(recipeUnmarsh);

		return recipes;

	}

}
