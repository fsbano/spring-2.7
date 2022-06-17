package io.fsbano;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

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
  
  String url = "/api/contact";

  @Test
  void APIRestContact() throws Exception {

     // New Contact
     mockMvc.perform(
             post(url)
             .accept("application/json")
             .contentType("application/json")
             .content("{ \"name\":\"Fabio Sbano\", \"email\":\"fsbano@yahoo.com\" }")
            ).andExpect(status().isOk())
            .andExpect(jsonPath("$.name", is("Fabio Sbano")));

     // Get Contact
     mockMvc.perform(
             get(url)
             .accept("application/json")
             .contentType("application/json")
            ).andExpect(status().isOk())
            .andExpect(content().string(containsString("Fabio Sbano")));

     // Update Contact
     MvcResult result = mockMvc.perform(
             get(url)
             .accept("application/json")
             .contentType("application/json")
            ).andExpect(status().isOk())
            .andExpect(content().string(containsString("Fabio Sbano"))).andReturn();
     String responseAsString = result.getResponse().getContentAsString();
     ObjectMapper mapper = new ObjectMapper();
     Contact[] contact = mapper.readValue(responseAsString, Contact[].class);
     mockMvc.perform(
             put("/api/contact/" + contact[0].getId())
             .accept("application/json")
             .contentType("application/json")
             .content("{ \"name\":\"Fabio S. Sbano\", \"email\":\"fsbano@gmail.com\" }")
            ).andExpect(status().isOk())
            .andExpect(content().string(containsString("Fabio S. Sbano")));

     // Delete Contact
     result = mockMvc.perform(
             get(url)
             .accept("application/json")
             .contentType("application/json")
            ).andExpect(status().isOk())
            .andExpect(content().string(containsString("Fabio S. Sbano"))).andReturn();
     responseAsString = result.getResponse().getContentAsString();
     mapper = new ObjectMapper();
     contact = mapper.readValue(responseAsString, Contact[].class);
     mockMvc.perform(
             delete("/api/contact/" + contact[0].getId())
             .accept("application/json")
             .contentType("application/json")
            ).andExpect(status().isOk());
  }
}
