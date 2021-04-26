package cn.edu.scau.ticket.application;

import cn.edu.scau.ticket.application.beans.result.ResultEntity;
import cn.edu.scau.ticket.application.beans.result.ResultStatus;
import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@SpringBootTest
class ApplicationTests {

    @Test
    void contextLoads() throws NoSuchAlgorithmException, UnsupportedEncodingException {

    }

    @Test
    void testJsonConvert() {
        ResultEntity loginSuccess = ResultEntity.getResultEntity(ResultStatus.LOGIN_SUCCESS);
        String s = JSON.toJSONString(loginSuccess);
        System.out.println(s);
    }
}
