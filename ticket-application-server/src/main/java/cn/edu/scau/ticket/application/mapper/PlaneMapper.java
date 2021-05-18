package cn.edu.scau.ticket.application.mapper;

import cn.edu.scau.ticket.application.beans.Plane;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlaneMapper {
    Plane getPlaneById(Integer id);

    List<Plane> getAllPlanes();
}
