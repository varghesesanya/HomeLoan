package com.barclays.homeloans;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.barclays.homeloans.controller.UserController;
import com.barclays.homeloans.model.User;
import com.barclays.homeloans.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class)
@WithMockUser
class UserControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	User mockUser = new User("Geet Shingi", "geet@gmail.com", "geet@1234");
	String mockString = "Hello Geet Shingi";
		
	String exampleUser = "{\"name\":\"Geet Shingi\",\"email\":\"geet@gmail.com\",\"password\":\"geet@1234\"}";
	
	
	@Test
	void registercontroller() throws Exception{
		
		
		Mockito.when(userService.registerUser(Mockito.any(User.class))).thenReturn(mockString);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/register/")
				.accept(MediaType.APPLICATION_JSON).content(exampleUser)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		System.out.println("RESPONSE: " + response.getStatus() + "CREATED" + HttpStatus.OK.value());
		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}
	
	@Test
	void logincontroller() throws Exception{
		
		Mockito.when(userService.registerUser(Mockito.any(User.class))).thenReturn(mockString);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/login/")
				.accept(MediaType.APPLICATION_JSON).content(exampleUser)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		System.out.println("RESPONSE: " + response.getStatus() + "CREATED" + HttpStatus.OK.value());
		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}
	
	@Test
	void mailcontroller() throws Exception{
		
		Mockito.when(userService.registerUser(Mockito.any(User.class))).thenReturn(mockString);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/sendMail/")
				.accept(MediaType.APPLICATION_JSON).content(exampleUser)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		System.out.println("RESPONSE: " + response.getStatus() + "CREATED" + HttpStatus.OK.value());
		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

}
