package cn.edu.scau.ticket.application.handler;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/5/20
 */
public class MyDateFormatter implements Formatter<Date> {

    private String pattern;
    private boolean begin;

    public MyDateFormatter(String pattern, boolean begin) {
        this.pattern = pattern;
        this.begin = begin;
    }

    @Override
    public Date parse(String text, Locale locale) throws ParseException {
        long time = new SimpleDateFormat(pattern).parse(text).getTime();
        if (!begin) {
            time += (23 * 60 * 60 + 59 * 60 + 59) * 1000;
        }
        return new Date(time);
    }

    @Override
    public String print(Date object, Locale locale) {
        return object.toString();
    }
}
