package cn.edu.scau.ticket.application.annotation;

import java.lang.annotation.*;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/5/20
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
public @interface MyDateFormat {
    String pattern() default "yyyy-MM-dd";
    boolean begin() default true;
}
