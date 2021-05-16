package cn.edu.scau.ticket.application.beans.qo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/5/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("flightCriteria")
public class FlightCriteria {
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date leaveTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date arriveTime;
    private String leaveAirportName;
    private String arriveAirportName;
}
