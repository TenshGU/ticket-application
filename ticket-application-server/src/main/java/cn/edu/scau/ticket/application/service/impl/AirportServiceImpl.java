package cn.edu.scau.ticket.application.service.impl;

import cn.edu.scau.ticket.application.beans.Airport;
import cn.edu.scau.ticket.application.beans.result.ResultEntity;
import cn.edu.scau.ticket.application.beans.result.ResultStatus;
import cn.edu.scau.ticket.application.mapper.AirportMapper;
import cn.edu.scau.ticket.application.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/5/16
 */
@Service
public class AirportServiceImpl implements AirportService {
    @Autowired
    private AirportMapper airportMapper;

    @Override
    public ResultEntity getAllAirports() {
        List<Airport> allAirports = airportMapper.getAllAirports();
        return ResultEntity.getResultEntity(ResultStatus.SUCCESS)
                .addInfo("total",allAirports.size()).addInfo("airports",allAirports);
    }
}
