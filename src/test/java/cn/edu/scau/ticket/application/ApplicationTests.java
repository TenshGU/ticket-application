package cn.edu.scau.ticket.application;

import cn.edu.scau.ticket.application.beans.ResultEntity;
import cn.edu.scau.ticket.application.beans.ResultStatus;
import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import sun.security.krb5.internal.crypto.HmacSha1Aes256CksumType;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
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
