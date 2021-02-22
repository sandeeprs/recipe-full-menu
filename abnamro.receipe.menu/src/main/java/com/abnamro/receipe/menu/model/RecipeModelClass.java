package com.abnamro.receipe.menu.model;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RecipeModelClass {
	
	
	private int id;
	
	@NotBlank(message = "must not be blank")
	private String name;
	
	@NotBlank(message ="must not be blank")
	private String description;
	
	@NotBlank(message ="must not be empty")
	private String foodType;
	
	@Min(value = 1)
	private int headIntake;
	
	private String dateCreated;
	
	@NotBlank
	private String instructions;
	
	@NotEmpty
	private List<String> ingredients;
	
	
	public RecipeModelClass() {}

	public RecipeModelClass(int id, String name, String description, String foodType, int headIntake,
			String dateCreated, String instructions) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.foodType = foodType;
		this.headIntake = headIntake;
		this.dateCreated = dateCreated;
		this.instructions = instructions;
	}

	public RecipeModelClass(String name,String description,String foodType,
			int headIntake, String dateCreated, String instructions,
			List<String> ingredients) {
		this.name = name;
		this.description = description;
		this.foodType = foodType;
		this.headIntake = headIntake;
		this.dateCreated = dateCreated;
		this.instructions = instructions;
		this.ingredients = ingredients;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFoodType() {
		return foodType;
	}

	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}

	public int getHeadIntake() {
		return headIntake;
	}

	public void setHeadIntake(int headIntake) {
		this.headIntake = headIntake;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public List<String> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
	}

	@Override
	public String toString() {
		return "RecipeModelClass [id=" + id + ", name=" + name + ", description=" + description + ", foodType="
				+ foodType + ", headIntake=" + headIntake + ", dateCreated=" + dateCreated + ", instructions="
				+ instructions + ", ingredients=" + ingredients + "]";
	}
	
	
	

}
