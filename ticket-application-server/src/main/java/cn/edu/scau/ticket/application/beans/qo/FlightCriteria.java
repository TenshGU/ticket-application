package cn.edu.scau.ticket.application.beans.qo;

import cn.edu.scau.ticket.application.annotation.MyDateFormat;
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
    @MyDateFormat
    private Date leaveTime;
    @MyDateFormat(begin = false)
    private Date arriveTime;
    private String leaveAirportName;
    private String arriveAirportName;
}
