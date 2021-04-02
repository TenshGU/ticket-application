package cn.edu.scau.ticket.application;

import cn.edu.scau.ticket.application.beans.ResultStatus;
import cn.edu.scau.ticket.application.handler.Converter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testJsonConvert() {
        ResultStatus loginSuccess = ResultStatus.LOGIN_SUCCESS;
        String s = Converter.EnumtoJson(loginSuccess);
        System.out.println(s);
    }
}
