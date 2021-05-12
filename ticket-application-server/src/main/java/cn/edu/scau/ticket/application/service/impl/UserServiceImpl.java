package cn.edu.scau.ticket.application.service.impl;

import cn.edu.scau.ticket.application.beans.User;
import cn.edu.scau.ticket.application.beans.UserAuthority;
import cn.edu.scau.ticket.application.beans.result.ResultEntity;
import cn.edu.scau.ticket.application.beans.result.ResultStatus;
import cn.edu.scau.ticket.application.mapper.AuthMapper;
import cn.edu.scau.ticket.application.mapper.UserMapper;
import cn.edu.scau.ticket.application.service.AuthService;
import cn.edu.scau.ticket.application.service.UserService;
import cn.edu.scau.ticket.application.utils.FastDFSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/5/7
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthService authService;

    @Autowired
    private FastDFSUtil fastDFSUtil;

    @Override
    public ResultEntity saveUser(User user, MultipartFile file) {
        String encryPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encryPassword);
        String image = null;
        try {
            image = fastDFSUtil.uploadFile(file,true);
        } catch (Exception e) {
            image = DEFAULT_IMG_PATH;
        }
        //设置图片url
        user.setImage(image);
        //设置默认权限
        user.setAuthorities(DEFAULT_AUTHORITIES);
        //设置组id
        List<Integer> groupIdsByRoleName = authService.getGroupIdsByRoleName(DEFAULT_AUTHORITIES);
        user.setGroupIds(groupIdsByRoleName);

        userMapper.saveUser(user);

        return ResultEntity.getResultEntity(ResultStatus.SUCCESS);
    }

    @Override
    public ResultEntity validUserError(BindingResult bindingResult) {
        Map<String, Object> map = new HashMap<>();
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        for (ObjectError oe : allErrors) {
            String key = null;
            String msg = null;
            if (oe instanceof FieldError) {
                key = ((FieldError) oe).getField();
            } else {
                key = oe.getObjectName();
            }
            msg = oe.getDefaultMessage();
            map.put(key,msg);
        }
        return ResultEntity
                .getResultEntity(ResultStatus.FAILED)
                .addInfo(map);
    }

    String code =null;
    int width = 100;//图片宽度
    int height = 50;//图片高度
    Random random = new Random();
    private String[] fontNames  = {"宋体", "微软雅黑", "Arial", "Tahoma", "Verdana", "微软雅黑", "Times New Roman"};

    private Color randomColor() //产生随机颜色
    {
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        return new Color(red, green, blue);
    }

    private Font randomFont() //产生随机字体
    {
        String fontName = fontNames[random.nextInt(fontNames.length)];
        int style = random.nextInt(4);
        int fontSize = random.nextInt(10) + 24;
        return new Font(fontName, style, fontSize);
    }

    @Override
    public void generateVerifyCode(HttpServletRequest req, HttpServletResponse response) throws IOException
    {

        response.setCharacterEncoding("utf-8");
        BufferedImage image = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();//画笔对象

        //设置背景色
        graphics.setColor(randomColor());
        graphics.fillRect(0,0,width,height);

        //画边框
        graphics.setColor(Color.black);
        graphics.drawRect(0,0,width - 1,height - 1);

        String charSet = "0123456789abcdefghigklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";//字符集
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 1; i <= 4; i++) //生成6位验证码
        {
            graphics.setColor(randomColor());
            graphics.setFont(randomFont());
            int index = random.nextInt(charSet.length());
            stringBuffer.append(charSet.charAt(index));
            graphics.drawString(charSet.charAt(index)+"",width/5*i,height/2);
            //System.out.println(stringBuffer);
            req.getSession().setAttribute("verifycode",stringBuffer);//存放到session里
            System.out.println("123test  :"+req.getSession().getAttribute("verifycode"));
            code=stringBuffer.toString();
        }


        //干扰背景
        for (int i = 0; i < 10; i++)
        {
            graphics.setColor(randomColor());
            graphics.drawLine(random.nextInt(width),random.nextInt(width),random.nextInt(height),random.nextInt(height));
        }
        //3.将图片输出到页面展示
        ImageIO.write(image,"jpg",response.getOutputStream());
    }


    public String getCode() {
        return code;
    }
}
