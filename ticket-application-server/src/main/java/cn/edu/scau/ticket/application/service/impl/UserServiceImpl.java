package cn.edu.scau.ticket.application.service.impl;

import cn.edu.scau.ticket.application.beans.User;
import cn.edu.scau.ticket.application.beans.result.ResultEntity;
import cn.edu.scau.ticket.application.beans.result.ResultStatus;
import cn.edu.scau.ticket.application.mapper.UserMapper;
import cn.edu.scau.ticket.application.service.AuthService;
import cn.edu.scau.ticket.application.service.UserService;
import cn.edu.scau.ticket.application.utils.FastDFSUtil;
import cn.edu.scau.ticket.application.utils.JsonWriter;
import cn.edu.scau.ticket.application.utils.NetWorkUtil;
import cn.edu.scau.ticket.application.utils.VerifyCodeUtil;
import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private VerifyCodeUtil verifyCodeUtil;

    @Autowired
    private FastDFSUtil fastDFSUtil;

    @Autowired
    private Redisson redisson;

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

    @Override
    public ResultEntity getUserInfo(String username) {
        User user = (User) userDetailsService.loadUserByUsername(username);
        Map<String, Object> userInfo = JsonWriter.convertObj2MapInfo(User.class, user);
        //移除隐私和无关信息
        userInfo.remove("password");
        userInfo.remove("groupIds");
        return ResultEntity
                .getResultEntity(ResultStatus.SUCCESS)
                .addInfo(userInfo);
    }

    @Override
    public void generateVerifyCode(HttpServletRequest request, HttpServletResponse response) {
        String realIP = NetWorkUtil.getClientRealIP(request);
        String vCode = verifyCodeUtil.generateVerifyCode(response);
        RBucket<String> bucket = redisson.getBucket(realIP + ":login:vCode");
        bucket.set(vCode, 1L, TimeUnit.MINUTES);
    }
}
