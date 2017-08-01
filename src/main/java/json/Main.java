package json;

import json.interfaces.IConvertJson;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Human human = new Human("FirstName", "LastName",
                "football", LocalDate.of(1991, 06, 13));
        IConvertJson convertJson = new ConvertJson();
        String toJson = convertJson.toJson(human);
        System.out.println(toJson);
        String fromJson = String.valueOf(convertJson.fromJson(toJson, human.getClass()));
        System.out.println(fromJson);
    }
}
