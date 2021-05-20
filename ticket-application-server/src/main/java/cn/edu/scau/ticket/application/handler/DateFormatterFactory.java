package cn.edu.scau.ticket.application.handler;

import cn.edu.scau.ticket.application.annotation.MyDateFormat;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/5/20
 */
@Component
public class DateFormatterFactory implements AnnotationFormatterFactory<MyDateFormat> {

    @Override
    public Set<Class<?>> getFieldTypes() {
        Set<Class<?>> fieldTypes = new HashSet<>();
        fieldTypes.add(Date.class);
        return fieldTypes;
    }

    @Override
    public Printer<?> getPrinter(MyDateFormat annotation, Class<?> fieldType) {
        return new MyDateFormatter(annotation.pattern(), annotation.begin());
    }

    @Override
    public Parser<?> getParser(MyDateFormat annotation, Class<?> fieldType) {
        return new MyDateFormatter(annotation.pattern(),annotation.begin());
    }
}