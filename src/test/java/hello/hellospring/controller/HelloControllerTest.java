package hello.hellospring.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HelloController.class)
class HelloControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	void helloApi() throws Exception {
		// given
		String name = "namename";

		// when
		final ResultActions resultActions = mvc.perform(get("/hello-api").param("name", name)
			.contentType(MediaType.APPLICATION_JSON_UTF8))
			.andDo(print());

		// then
		resultActions.andExpect(status().isOk())
					 .andExpect(jsonPath("name").value("namename"))
		;
	}
}