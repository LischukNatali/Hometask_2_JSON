package json.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//        аннотация @CustomDateFormat должна задавать форматированный вывод
//        формата времени. Если есть поле даты, но без указанного формата -
//        использовать формат по умолчания (вывод через toString)

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CustomDateFormat {
    String format() default "MM-dd-yyyy";
}
