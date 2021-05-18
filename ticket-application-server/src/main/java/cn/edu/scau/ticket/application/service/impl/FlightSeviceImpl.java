package cn.edu.scau.ticket.application.service.impl;

import cn.edu.scau.ticket.application.beans.Flight;
import cn.edu.scau.ticket.application.beans.qo.FlightCriteria;
import cn.edu.scau.ticket.application.beans.result.ResultEntity;
import cn.edu.scau.ticket.application.beans.result.ResultStatus;
import cn.edu.scau.ticket.application.mapper.FlightMapper;
import cn.edu.scau.ticket.application.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/5/15
 */
@Service
public class FlightSeviceImpl implements FlightService {
    @Autowired
    private FlightMapper flightMapper;

    @Override
    public ResultEntity addFlight(Flight flight) {
        flight.setId(UUID.randomUUID().toString());
        flightMapper.insertFlight(flight);
        return ResultEntity.getResultEntity(ResultStatus.SUCCESS);
    }

    @Override
    public ResultEntity deleteFlightById(String flightId) {
        flightMapper.deleteFlightById(flightId);
        return ResultEntity.getResultEntity(ResultStatus.SUCCESS);
    }

    @Override
    public ResultEntity searchFlightByCondition(FlightCriteria flightCriteria) {
        List<Flight> flights = flightMapper.getFlightByCondition(flightCriteria);
        ResultEntity resultEntity = ResultEntity.getResultEntity(ResultStatus.SUCCESS);
        resultEntity.addInfo("total",flights.size());
        resultEntity.addInfo("flights",flights);
        return resultEntity;
    }

    @Override
    public ResultEntity updateFlight(Flight flight) {
        flightMapper.updateFlight(flight);
        return ResultEntity.getResultEntity(ResultStatus.SUCCESS);
    }
}
