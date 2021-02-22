package com.abnamro.receipe.menu.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "recipes")
public class Recipes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "food_type")
	private String foodType;
	
	@Column(name = "head_intake")
	private int headIntake;
	
	@Column(name = "date_created")
	private String dateCreated;
	
	@Column(name = "instructions")
	private String instructions;
	
	@Column(name = "ingredients")
	private String ingredients;
	
	//Default constructor
	public Recipes() {}
	
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

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	@Override
	public String toString() {
		return "Recipes [id=" + id + ", name=" + name + ", description=" + description + ", foodType=" + foodType
				+ ", headIntake=" + headIntake + ", dateCreated=" + dateCreated + ", instructions=" + instructions
				+ ", ingredients=" + ingredients + "]";
	}
	
	

}
