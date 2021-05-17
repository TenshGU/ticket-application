package cn.edu.scau.ticket.application.service.impl;

import cn.edu.scau.ticket.application.beans.Plane;
import cn.edu.scau.ticket.application.beans.result.ResultEntity;
import cn.edu.scau.ticket.application.beans.result.ResultStatus;
import cn.edu.scau.ticket.application.mapper.PlaneMapper;
import cn.edu.scau.ticket.application.service.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/5/17
 */
@Service
public class PlaneServiceImpl implements PlaneService {
    @Autowired
    private PlaneMapper planeMapper;

    @Override
    public ResultEntity getAllPlanes() {
        List<Plane> planes = planeMapper.getAllPlanes();
        return ResultEntity.getResultEntity(ResultStatus.SUCCESS)
                .addInfo("total",planes.size()).addInfo("planes",planes);
    }
}
