package com.csc340.individualAssignment2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author dallin
 */
@RestController
public class RestApiController {
    
    /**
     * the chosen API is the agify api from https://api.agify.io     * 
     * have agify guess the persons age by their first name
     * 
     * @param name the request parameter
     * @return the json response
     */
    @GetMapping("/age")
    public Object getAge(@RequestParam(value = "name", defaultValue = "michael") String name) {
        try{
            String url = "https://api.agify.io?name=" + name;
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();
            
            String jSonAge = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(jSonAge);            
            
            //Print the whole response to console.
            System.out.println(root);
            
            //Print the important information
            String age = root.get("age").asText();
            String count = root.get("count").asText();
            String printName = root.get("name").asText();
            System.out.println("Name: " + printName);
            System.out.println("Count: " + count);
            System.out.println("Age: " + age);
            
            //Simple output using the information from the returned json
            System.out.println("hmmm... " + printName + " I've seen " + count + " " + printName 
                                + "'s before. I guess you're about... " + age + ", am I right?");
            
            
            return root;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestApiController.class.getName()).log(Level.SEVERE, null, ex);
            return "error in /age";
        }
    
    }

}
