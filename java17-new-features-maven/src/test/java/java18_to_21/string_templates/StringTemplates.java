package java18_to_21.string_templates;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static java.lang.StringTemplate.STR;

public class StringTemplates {

    String name = "Mahdi";

    @Test
    void testStringTemplates() {
        // let message = "Greetings {{ name }}!";  			//TypeScript

        String name = "Mahdi";

        //concatenation
        String message = "Greetings " + name + "!";
        //String.format()
        message = String.format("Greetings %s!", name);    //concatenation
        //StringBuilder
        message = new StringBuilder().append("Greetings ").append(name).append("!").toString();

        //variable
        message = STR."Greetings \{name}!";

        //method
        message = STR."Greetings \{getName()}!";

        //field
        message = STR."Greetings \{this.name}!";

        System.out.println(message);

        int x = 10, y = 20;
        String s = STR."\{x} + \{y} = \{x + y}";	//"10 + 20 = 30"
        System.out.println(s);
    }

    public String getName() {
        return name;
    }

}
