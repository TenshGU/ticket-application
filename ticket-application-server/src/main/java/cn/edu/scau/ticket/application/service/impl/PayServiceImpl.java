package cn.edu.scau.ticket.application.service.impl;

import cn.edu.scau.ticket.application.beans.Flight;
import cn.edu.scau.ticket.application.beans.Order;
import cn.edu.scau.ticket.application.beans.result.ResultEntity;
import cn.edu.scau.ticket.application.beans.result.ResultStatus;
import cn.edu.scau.ticket.application.config.AliPayConfig;
import cn.edu.scau.ticket.application.mapper.FlightMapper;
import cn.edu.scau.ticket.application.mapper.OrderMapper;
import cn.edu.scau.ticket.application.service.PayService;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/5/15
 */
@Service
public class PayServiceImpl implements PayService {
    @Autowired
    private AliPayConfig aliPayConfig;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private FlightMapper flightMapper;

    @Override
    public void sendPayment(Flight flight, String username, HttpServletResponse response) {
        //实例化客户端,填入所需参数
        AlipayClient alipayClient =
                new DefaultAlipayClient(aliPayConfig.getGatewayUrl(), aliPayConfig.getAppId(), aliPayConfig.getPrivateKey(),
                        aliPayConfig.getFormat(), aliPayConfig.getCharset(), aliPayConfig.getPublicKey(), aliPayConfig.getSignType());
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        //在公共参数中设置回跳和通知地址
        request.setReturnUrl(aliPayConfig.getReturnUrl());
        request.setNotifyUrl(aliPayConfig.getNotifyUrl());
        //商户订单号，商户网站订单系统中唯一订单号，必填，生成随机Id
        String outTradeNo = UUID.randomUUID().toString() + flight.getId() + "@" + username;
        //付款金额，必填
        String totalAmount = String.valueOf(flight.getPrice());
        //订单名称，必填
        String subject = flight.getName();
        //商品描述，可空
        String body = username + "购买" + "从" + flight.getLeaveAirportName() + "到" + flight.getArriveAirportName() + "的机票";
        request.setBizContent("{\"out_trade_no\":\""+ outTradeNo +"\","
                + "\"total_amount\":\""+ totalAmount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        try {
            // 调用SDK生成表单
            String form = alipayClient.pageExecute(request).getBody();
            response.setContentType("text/html;charset=" + aliPayConfig.getCharset());
            // 直接将完整的表单html输出到页面
            response.getWriter().write(form);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 注意：项目仅个人项目测试接入 支付接口，此处没有进行验证签名
     * @param request
     * @return
     */
    @Transactional
    @Override
    public ResultEntity validAndSaveOrder(HttpServletRequest request) {
        //商户订单号
        String orderId = new String(request.getParameter("out_trade_no").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

        //付款金额
        String totalAmount = new String(request.getParameter("total_amount").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

        String[] strs = orderId.substring(36).split("@");
        String flightId = strs[0];
        String username = strs[1];

        Flight flight = new Flight();
        flight.setId(flightId);

        Order order = new Order();
        order.setId(orderId.substring(0,36));
        order.setPrice(Double.valueOf(totalAmount));
        order.setUsername(username);
        order.setFlight(flight);
        //插入订单
        orderMapper.insertOrder(order);
        //减少库存
        flightMapper.decFlightStockById(flightId);

        return ResultEntity.getResultEntity(ResultStatus.SUCCESS);
    }
}
