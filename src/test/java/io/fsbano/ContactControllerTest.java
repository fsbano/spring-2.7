package io.fsbano;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ContactController.class)
public class ContactControllerTest {
  @Autowired
	private MockMvc mockMvc;

  @MockBean
	private ContactController controller;

  @Test
  void whenCreateUser() throws Exception {

  }
}
