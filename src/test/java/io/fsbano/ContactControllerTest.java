package io.fsbano;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ContactControllerTest {
  @Autowired
  private MockMvc mockMvc;
  
  @Test
  void newContact() throws Exception {
     String url = "/api/contact";
     mockMvc.perform(
             post(url)
             .accept("application/json")
             .contentType("application/json")
             .content("{ \"name\":\"Fabio Sbano\", \"email\":\"fsbano@yahoo.com\" }")
            ).andExpect(status().isOk())
            .andExpect(jsonPath("$.name", is("Fabio Sbano")));
  }

  @Test
  void getContact() throws Exception {
     String url = "/api/contact";
     mockMvc.perform(
             get(url)
             .accept("application/json")
             .contentType("application/json")
            ).andExpect(status().isOk())
            .andExpect(content().string(containsString("Fabio Sbano")));
  }

}
