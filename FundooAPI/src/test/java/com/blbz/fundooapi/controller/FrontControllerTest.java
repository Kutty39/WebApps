package com.blbz.fundooapi.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.ResourceUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@AutoConfigureMockMvc
@WebMvcTest
class FrontControllerTest {
    @Autowired
    ObjectMapper objectMapper;
    /*    @Value("classpath:NagTestCasesRegiter.json")
        Resource resourceFile;*/
    @Autowired
    MockMvc mockMvc;

    @Test
    void registerNagativeTestCase() throws Exception {
        JsonNode jsonNode = objectMapper.readTree(ResourceUtils.getFile("TestCase/NagTestCasesRegiter.json"));
        //int i=0;
        for (JsonNode node : jsonNode) {
            if (node != null) {
               // System.out.println(i+""+node);
                //++i;
                MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/register").contentType(MediaType.APPLICATION_JSON_VALUE).content(String.valueOf(node))).andReturn();
                //System.out.println(result.getResponse().getStatus());
                assertNotEquals(200,result.getResponse().getStatus());
            }
        }

    }
    @Test
    void registerestCase() throws Exception {
        JsonNode jsonNode = objectMapper.readTree(ResourceUtils.getFile("TestCase/PasTestCasesRegiter.json"));
       // int i=0;
        for (JsonNode node : jsonNode) {
            if (node != null) {
                //System.out.println(i+""+node);
                //++i;
                MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/register").contentType(MediaType.APPLICATION_JSON_VALUE).content(String.valueOf(node))).andReturn();
                //System.out.println(result.getResponse().getStatus());
                assertEquals(200,result.getResponse().getStatus());
            }
        }

    }
}