package cn.edu.scau.ticket.application.service;

import cn.edu.scau.ticket.application.beans.Flight;
import cn.edu.scau.ticket.application.beans.qo.FlightCriteria;
import cn.edu.scau.ticket.application.beans.result.ResultEntity;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/5/15
 */
public interface FlightService {
    ResultEntity addFlight(Flight flight);

    ResultEntity deleteFlightById(String flightId);

    ResultEntity searchFlightByCondition(FlightCriteria flightCriteria);

    ResultEntity updateFlight(Flight flight);
}
