package com.abnamro.receipe.menu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.abnamro.receipe.menu.model.RecipeModelClass;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TheReceipeMenuApplication.class)
@WebAppConfiguration
class TheReceipeMenuApplicationTests {

	protected MockMvc mvc;

	@Autowired
	WebApplicationContext webApplicationContext;

	@PostConstruct
	protected void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	void getAllRecipes() throws Exception {
		String uri = "/recipes";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		RecipeModelClass[] testModel = mapFromJson(content, RecipeModelClass[].class);
		assertTrue(testModel.length > 0);
	}

	@Test
	void getRecipe() throws Exception {
		String uri = "/recipes/1";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		RecipeModelClass testModel = mapFromJson(content, RecipeModelClass.class);
		assertEquals(2,testModel.getId());
	}

	@Test
	void getNotAvailableRecipe() throws Exception {
		String uri = "/recipes/20";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(404, status);
	}

	@Test
	void addRecipe() throws Exception {
		String uri = "/recipes";

		List<String> ingred = new ArrayList<String>(){
			{add("Water");
			add("Rice");
			add("UradDal"); }};

			RecipeModelClass rmc = new RecipeModelClass("Dosa","A south Indian dish","veg",2,"20-02-2021 13:54","Take batter. Put it in the cooker. Steam it for 15 minutes. Serve with dosa nad chatni",ingred);
			String inputJson = mapToJson(rmc);
			MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.content(inputJson)).andReturn();

			int status = mvcResult.getResponse().getStatus();
			assertEquals(200, status);
	}

	@Test
	void RequestExceptionforAddRecipe() throws Exception {
		String uri = "/recipes";

		List<String> ingred = new ArrayList<String>(){
			{add("Water");
			add("Rice");
			add("UradDal"); }};

			RecipeModelClass rmc = new RecipeModelClass("Idli","A south Indian dish","",2,"20-02-2021 13:54","Take batter. Put it in the cooker. Steam it for 15 minutes. Serve with dosa nad chatni",ingred);
			String inputJson = mapToJson(rmc);
			MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.content(inputJson)).andReturn();

			int status = mvcResult.getResponse().getStatus();
			assertEquals(400, status);
	}

	@Test
	void updateProduct() throws Exception {
		String uri = "/recipes/2";
		List<String> ingred = new ArrayList<String>(){
			{add("potato");
			add("water");
			add("red chilli");
			add("salt");
			add("garam masala");}};

			RecipeModelClass rmc = new RecipeModelClass("Dum Aloo","A north Indian dish","veg",5,"20-02-2021 13:54","Take potato.Boil it. Crush it and mix it with red chilli, salt and garam masala.cook it on light flame for 15 minutes after adding water",ingred);
			String inputJson = mapToJson(rmc);
			MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.content(inputJson)).andReturn();

			int status = mvcResult.getResponse().getStatus();
			assertEquals(200, status);
			String content = mvcResult.getResponse().getContentAsString();
			RecipeModelClass testModel = mapFromJson(content, RecipeModelClass.class);
			assertTrue(testModel.getId() > 0);
	}

	@Test
	void deleteProduct() throws Exception {
		String uri = "/recipes/3";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals("Data deleted successfully!",content);
	}

	@Test
	void deleteNotAvailableRecipe() throws Exception {
		String uri = "/recipes/20";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(404, status);
	}

	protected String mapToJson(RecipeModelClass obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}

}
