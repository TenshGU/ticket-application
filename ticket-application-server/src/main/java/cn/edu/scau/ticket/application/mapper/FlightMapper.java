package cn.edu.scau.ticket.application.mapper;

import cn.edu.scau.ticket.application.beans.Flight;
import cn.edu.scau.ticket.application.beans.qo.FlightCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FlightMapper {
    int insertFlight(Flight flight);

    int deleteFlightById(String flightId);

    List<Flight> getFlightByCondition(FlightCriteria flightCriteria);

    int updateFlight(Flight flight);
}