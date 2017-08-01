package json.interfaces;

public interface IConvertJson {

//      может принимать любой объект и возвращать его JSON-представление
//      {"firstName": "Ivan","lastName": "Ivanov","fun": "Guitar","birthDate": "01-09-1990" }
    public String toJson(Object o) throws IllegalAccessException;

//    Реализовать механизм вычитки данных из строки JSON’а. Реализовать метод fromJson(String json, Class<T> clazz),
//    где json - входящая JSON строка, а clazz - класс в который нужно распарсить входящие данные.
//    String fromJson = "{\"firstName\":\"fName\",\"lastName\":\"lName\",\"hobby\":\"hb\", \"birthDate\":\"01012011\"}";
    public Object fromJson(String json, Class clazz) throws IllegalAccessException, InstantiationException;
}

