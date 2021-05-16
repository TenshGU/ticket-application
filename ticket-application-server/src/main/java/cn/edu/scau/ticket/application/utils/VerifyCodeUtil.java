package cn.edu.scau.ticket.application.utils;

import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @description: 验证码实用类
 * @author: Monkey
 * @createDate: 2021/5/12
 */
@Component
public class VerifyCodeUtil {
    //图片宽度
    private final int WIDTH = 100;
    //图片高度
    private final int HEIGHT = 50;
    //字符集
    private final String CHARSET = "0123456789abcdefghigklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    //字体
    private final String[] FONTNAMES  = {"宋体", "微软雅黑", "Arial", "Tahoma", "Verdana", "微软雅黑", "Times New Roman"};
    //随机数对象
    private Random random = new Random();

    /**
     * 产生随机颜色
     * @return 颜色
     */
    private Color randomColor() {
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        return new Color(red, green, blue);
    }

    /**
     * 产生随机字体
     * @return 字体
     */
    private Font randomFont() {
        String fontName = FONTNAMES[random.nextInt(FONTNAMES.length)];
        int style = random.nextInt(4);
        int fontSize = random.nextInt(10) + 24;
        return new Font(fontName, style, fontSize);
    }

    /**
     * 生成验证码，并通过响应流返回
     * @param response 响应
     * @return 验证码
     */
    public String generateVerifyCode(HttpServletResponse response) {
        response.setContentType("image/jpeg");
        response.setHeader("Pragma","No-cache");
        response.setHeader("Cache-Contro","no-cache");
        response.setDateHeader("Expire",0);
        response.setCharacterEncoding("utf-8");
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();//画笔对象

        //设置背景色
        graphics.setColor(randomColor());
        graphics.fillRect(0,0,WIDTH,HEIGHT);

        //画边框
        graphics.setColor(Color.black);
        graphics.drawRect(0,0,WIDTH - 1,HEIGHT - 1);

        StringBuilder sb = new StringBuilder();
        //生成6位验证码
        for (int i = 1; i <= 4; i++) {
            graphics.setColor(randomColor());
            graphics.setFont(randomFont());
            int index = random.nextInt(CHARSET.length());
            sb.append(CHARSET.charAt(index));
            graphics.drawString(CHARSET.charAt(index)+"",WIDTH/5*i,HEIGHT/2);
        }
        //获得6位验证码
        String code = sb.toString();

        //干扰背景
        for (int i = 0; i < 10; i++) {
            graphics.setColor(randomColor());
            graphics.drawLine(random.nextInt(WIDTH),random.nextInt(WIDTH),random.nextInt(HEIGHT),random.nextInt(HEIGHT));
        }
        //3.将图片输出到页面展示
        try {
            ImageIO.write(image,"jpg",response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return code;
    }
}
