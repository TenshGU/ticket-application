package cn.edu.scau.ticket.application.service;

import cn.edu.scau.ticket.application.beans.User;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import java.util.Map;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/5/7
 */
public interface UserService {
    boolean saveUser(User user, MultipartFile file);

    Map<String, Object> getUserValidErrorMsg(BindingResult bindingResult);
}
