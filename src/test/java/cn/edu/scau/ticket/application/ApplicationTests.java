package cn.edu.scau.ticket.application;

import cn.edu.scau.ticket.application.beans.ResultEntity;
import cn.edu.scau.ticket.application.beans.ResultStatus;
import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testJsonConvert() {
        ResultEntity loginSuccess = ResultEntity.getResultEntity(ResultStatus.LOGIN_SUCCESS);
        String s = JSON.toJSONString(loginSuccess);
        System.out.println(s);
    }
}
