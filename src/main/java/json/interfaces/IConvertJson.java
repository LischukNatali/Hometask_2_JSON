package json.interfaces;

public interface IConvertJson {

//      Метод принимает любой объект и возвращать его JSON-представление
//      {"firstName":"Ivan","lastName":"Ivanov","fun":"Guitar","birthDate":"01-09-1990"}
    public String toJson(Object o) throws IllegalAccessException;

//    Метод считывает данные из строки JSON’а.
    public Object fromJson(String json, Class clazz) throws IllegalAccessException, InstantiationException;
}

