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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
}
