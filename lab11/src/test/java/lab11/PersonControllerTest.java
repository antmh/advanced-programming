package lab11;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import lab11.app.PersonController;

class PersonControllerTest {
	static MockMvc mvc;

	@BeforeAll
	static void setup() {
		mvc = MockMvcBuilders.standaloneSetup(new PersonController()).build();
	}

	@Test
	void addAndDelete() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/people/add").contentType(MediaType.APPLICATION_JSON)
				.content("{ \"name\": \"myName\" }")).andExpect(MockMvcResultMatchers.status().isOk());

		mvc.perform(MockMvcRequestBuilders.get("/people/all")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[*]").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$[*].name").isNotEmpty());

		mvc.perform(MockMvcRequestBuilders.delete("/people/name/myName"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void changeName() throws Exception {
		mvc.perform(MockMvcRequestBuilders.put("/people/name/oldName").param("name", "newName"))
				.andExpect(MockMvcResultMatchers.status().isGone());

		mvc.perform(MockMvcRequestBuilders.post("/people/add").contentType(MediaType.APPLICATION_JSON)
				.content("{ \"name\": \"oldName\" }")).andExpect(MockMvcResultMatchers.status().isOk());

		mvc.perform(MockMvcRequestBuilders.put("/people/name/oldName").param("name", "newName"))
				.andExpect(MockMvcResultMatchers.status().isOk());

		mvc.perform(MockMvcRequestBuilders.delete("/people/name/newName"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
}
