package cn.edu.scau.ticket.application.controller;

import cn.edu.scau.ticket.application.beans.result.ResultEntity;
import cn.edu.scau.ticket.application.beans.result.ResultStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/5/7
 */
@Controller
public class BusinessController {
    @PreAuthorize("hasRole('commonUser')")
    @GetMapping("/flightBook")
    public ResultEntity flightBook() {
        return ResultEntity.getResultEntity(ResultStatus.SUCCESS);
    }
}
