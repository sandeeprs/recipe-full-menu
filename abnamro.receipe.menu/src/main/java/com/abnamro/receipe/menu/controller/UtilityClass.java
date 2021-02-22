package com.abnamro.receipe.menu.controller;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.abnamro.receipe.menu.entity.Recipes;
import com.abnamro.receipe.menu.model.RecipeModelClass;

public class UtilityClass {
	
	//This class has sets of utility methods for input manipulation and generating expected response.
	
	/*
	 * This method converts Recipe java object into RecipeModelClass java object.
	 * Method takes the Recipe object as an argument and returns RecipeModelClass object.
	 */ 	
	public RecipeModelClass marshallingMethod(Recipes rec) {

		RecipeModelClass recipePojo = new RecipeModelClass();

		recipePojo.setId(rec.getId());
		recipePojo.setName(rec.getName());
		recipePojo.setDescription(rec.getDescription());
		recipePojo.setFoodType(rec.getFoodType());
		recipePojo.setHeadIntake(rec.getHeadIntake());
		recipePojo.setDateCreated(rec.getDateCreated());
		recipePojo.setInstructions(rec.getInstructions());
		recipePojo.setIngredients(ingredientToList(rec.getIngredients()));

		return recipePojo;

	}
	

	/*
	 * This method converts RecipeModelClass java object into Recipes java object.
	 * Method takes the RecipeModelClass object as an argument and returns Recipes object.
	 * This method is called for put and post method for manipulating and persisting incoming
	 * data into the table 
	 */ 
	public Recipes demarshallingMethod(RecipeModelClass rec) {

		Recipes res = new Recipes();

		res.setName(rec.getName());
		res.setDescription(rec.getDescription());
		res.setFoodType(rec.getFoodType());
		res.setHeadIntake(rec.getHeadIntake());
		res.setInstructions(rec.getInstructions());
		res.setIngredients(convertListToString(rec.getIngredients()));

		return res;

	}
	
	/* Converts string into a list */
	public List<String> ingredientToList(String ingredient){
		List<String> ingredientsList = null;
		if(ingredient != null) {		

			ingredientsList = (Arrays.asList(ingredient.split("\\.")));

			return ingredientsList;
		}
		else {
			return Collections.emptyList();
		}
	}

	/* Converts list to a string */
	public String convertListToString(List<String> ingredientList){

		String ingredients = null;

		if(!ingredientList.isEmpty()) {
			ingredients = String.join(".", ingredientList);
		}

		return ingredients;

	}

	/*
	 * Formats the date into the required format of "dd-MM-yyyy HH:mm" using
	 * SimpleDateFormat
	 * Takes current date as input and returns the formatted date as string
	 */	
	public String calculateDate(Date date) {

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		String strDate= formatter.format(date);

		return strDate;
	}

}
