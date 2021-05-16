package cn.edu.scau.ticket.application.mapper;

import cn.edu.scau.ticket.application.beans.Airport;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AirportMapper {
    List<Airport> getAllAirports();
}
