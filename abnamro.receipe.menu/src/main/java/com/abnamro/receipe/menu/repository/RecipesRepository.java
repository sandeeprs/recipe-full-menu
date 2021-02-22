package com.abnamro.receipe.menu.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.abnamro.receipe.menu.entity.Recipes;

@RepositoryRestResource(collectionResourceRel = "recipes",path = "recipes")
public interface RecipesRepository extends JpaRepository<Recipes, Integer> {

}
