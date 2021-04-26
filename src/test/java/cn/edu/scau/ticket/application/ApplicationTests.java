package cn.edu.scau.ticket.application;

import cn.edu.scau.ticket.application.beans.result.ResultEntity;
import cn.edu.scau.ticket.application.beans.result.ResultStatus;
import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class ApplicationTests {

    @Test
    void testPasswordEncoder() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("123456");
        System.out.println(password);
    }

    @Test
    void testJsonConvert() {
        ResultEntity loginSuccess = ResultEntity.getResultEntity(ResultStatus.LOGIN_SUCCESS);
        String s = JSON.toJSONString(loginSuccess);
        System.out.println(s);
    }
}
