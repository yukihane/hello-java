package examples.java;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

public class JavaMain {
    public static final String JSON_TEXT = "{\"name\": \"Bob\", \"age\": 30 }";

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new ParameterNamesModule());

        Person person = om.readValue(JSON_TEXT, Person.class);
        System.out.println(person);
    }
}

record Person(String name, int age) {
}
