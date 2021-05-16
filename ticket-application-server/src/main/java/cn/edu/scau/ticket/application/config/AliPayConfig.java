package cn.edu.scau.ticket.application.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/5/14
 */
@Configuration
@Data
public class AliPayConfig {
    @Value("${alipay.app_id}")
    private String appId;

    @Value("${alipay.private_key}")
    private String privateKey;

    @Value("${alipay.public_key}")
    private String publicKey;

    @Value("${alipay.format}")
    private String format;

    @Value("${alipay.return_url}")
    private String returnUrl;

    @Value("${alipay.notify_url}")
    private String notifyUrl;

    @Value("${alipay.sign_type}")
    public String signType;

    @Value("${alipay.charset}")
    public String charset;

    @Value("${alipay.gateway_url}")
    public String gatewayUrl;

    @Value("${alipay.log_path}")
    public String logPath;
}