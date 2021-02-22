package com.abnamro.receipe.menu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.abnamro.receipe.menu.entity.Recipes;
import com.abnamro.receipe.menu.exception.RecordNotFoundException;
import com.abnamro.receipe.menu.model.RecipeModelClass;
import com.abnamro.receipe.menu.service.RecipeInterface;

@RestController
public class RootControllerClass {

	private static final Logger log = LogManager.getLogger(RootControllerClass.class);
	
	@Autowired
	RecipeInterface recipeImp;
	
	/*
	 * Method to get user authenticated and authorize using inmemory authentication. 
	 * Returns ResponseEntity with 200(ok) code after successful authentication
	 */	
	@GetMapping(value= "/login")
    public ResponseEntity<String> guest() {
        return ResponseEntity.ok("This is login page!");
    }

	/*
	 * Method to get all the recipes. 
	 * Returns the list of json object of RecipeModelClass
	 */	
	@GetMapping("/recipes")
	public List<RecipeModelClass> getAllRecipes() {
		
		log.debug("Fetching all the recipes result");
		List<RecipeModelClass> recipeList = null;

		try {
			recipeList = recipeImp.getallRecipes();
		}catch(RecordNotFoundException e) {
			e.getMessage();
		}

		return recipeList;
	}
	
	/*
	 * Method to get a single recipe based on id.
	 * Id is passed as a path variable i.e /recipe/12 
	 * Returns json object of RecipeModelClass if found else throws RecordNotFoundException
	 */
	@GetMapping("/recipes/{id}")
	public RecipeModelClass getRecipe( @PathVariable int id) {
		
		log.debug("Fetching recipe for the Id | {}",id);
		RecipeModelClass recipe = recipeImp.getRecipe(id);

		if(recipe == null) {
			throw new RecordNotFoundException();
		}

		return recipe;
	}

	/*
	 * Method to add recipe in the table.
	 * Takes  RecipeModelClass json object as input
	 * Returns ResponseEntity based on the result
	 */
	@PostMapping("/recipes")
	public ResponseEntity<String> addRecipes(@Valid @RequestBody RecipeModelClass recipes) {
		
		log.debug("Inserting result into the data");
		Recipes returnObj = recipeImp.addRecipe(recipes);

		if(returnObj != null) {
			return ResponseEntity.ok("Required information has been persisted successfully for the Id :"+returnObj.getId());
		}
		else {
			return new ResponseEntity<>("Information entry failed",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*
	 * Method deletes a given recipe based on id.
	 * Id is passed as a path variable i.e /recipe/12 
	 * Returns ResponseEntity based on the result
	 */
	@DeleteMapping("/recipes/{id}")
	public ResponseEntity<String> deleteRecipe( @PathVariable int id) {
		
		log.debug("Deleting recipe for the Id | {}",id);
		boolean foo = recipeImp.deleteRecipe(id);
		
		if(foo) {
			return ResponseEntity.ok("Data deleted successfully!");
		}
		else {
			return new ResponseEntity<>("No data found for the id",HttpStatus.NOT_FOUND);
		}
	}

	/*
	 * Method to update recipe in the table based on id.
	 * Takes  RecipeModelClass json object as input and Id,too, is passed as a path variable
	 * Returns updated RecipeModelClass json object along with id
	 */
	@PutMapping("/recipes/{id}")
	public RecipeModelClass updateRecipe( @PathVariable int id,@Valid @RequestBody RecipeModelClass recipes ) {
		
		log.debug("Updating recipe for the Id | {}",id);
		RecipeModelClass recipeResponse = recipeImp.updateRecipe(id,recipes);

		return recipeResponse;
	}

	/*
	 * Exception resolver to handle MethodArgumentNotValidException for post and put methods
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
			MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}
}
