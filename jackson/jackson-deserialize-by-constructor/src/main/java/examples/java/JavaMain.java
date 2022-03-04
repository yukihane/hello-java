package examples.java;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import lombok.RequiredArgsConstructor;
import lombok.Value;

public class JavaMain {
    public static final String JSON_TEXT = "{\"name\": \"Bob\", \"age\": 30 }";

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new ParameterNamesModule());

        Person person = om.readValue(JSON_TEXT, Person.class);
        System.out.println(person);
    }
}

// https://github.com/projectlombok/lombok/issues/1842#issuecomment-419982012
@Value
@RequiredArgsConstructor(onConstructor = @__(@JsonCreator))
class Person {
    String name;
    int age;
}