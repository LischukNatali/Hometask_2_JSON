package json;

import json.annotations.CustomDateFormat;
import json.annotations.JsonValue;
import json.interfaces.IConvertJson;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ConvertJson implements IConvertJson {

    public String toJson(Object o) throws IllegalAccessException {
        String result = "{";
        Class clazz = o.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            if (field.get(o) != null) {

//            если поле содержит аннотацию @JsonValue, то имя JSON элемента
//            должно соответствовать имени, указанному в аннотации.
                if (field.isAnnotationPresent(JsonValue.class)) {
                    JsonValue jsonValue = field.getAnnotation(JsonValue.class);
                    result += "\"" + jsonValue.name() + "\":\"" + field.get(o) + "\",";

//                аннотация @CustomDateFormat должна задавать форматированный вывод формата времени. Если есть поле даты,
//                но без указанного формата - использовать формат по умолчания (вывод через toString)
                } else if (field.isAnnotationPresent(CustomDateFormat.class)) {
                    CustomDateFormat customDateFormat = field.getAnnotation(CustomDateFormat.class);
                    LocalDate localDate = (LocalDate) field.get(o);
                    result += "\"" + field.getName() + "\":\"" + localDate.format(DateTimeFormatter.ofPattern(customDateFormat.format())) + "\","; ///
                } else {
                    result += "\"" + field.getName() + "\":\"" + field.get(o) + "\",";
                }
                field.setAccessible(false);
            } else {
            }
        }
        result += "}";
        return result;
    }

    public Object fromJson(String json, Class clazz) throws IllegalAccessException, InstantiationException {
        Object result = clazz.newInstance();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            int startNameOfTheField = (json.indexOf(field.getName()) + field.getName().length() + 3);
            String findSymbol = "\"";
            String fieldValue = json.substring(startNameOfTheField, json.indexOf(findSymbol, startNameOfTheField));
            if (json.contains(field.getName())) {
                if (field.getName().equals("birthDate")) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    LocalDate date = LocalDate.parse(fieldValue, formatter);
                    field.set(result, date);
                } else {
                    System.out.println(json.substring(startNameOfTheField, json.indexOf(findSymbol, startNameOfTheField)));
                    field.set(result, fieldValue);
                }
                field.setAccessible(false);
            }
        }
            return result;
        }
    }

