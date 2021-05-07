package cn.edu.scau.ticket.application.service.impl;

import cn.edu.scau.ticket.application.beans.User;
import cn.edu.scau.ticket.application.service.UserService;
import cn.edu.scau.ticket.application.utils.FastDFSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/5/7
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private FastDFSUtil fastDFSUtil;

    private final String DEFAULT_IMG_PATH = "";

    @Override
    public boolean saveUser(User user, MultipartFile file) {
        String image = null;
        try {
            image = fastDFSUtil.uploadFile(file,true);
        } catch (Exception e) {
            image = DEFAULT_IMG_PATH;
        }
        user.setImage(image);
        return true;
    }
}
