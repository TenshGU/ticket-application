package cn.edu.scau.ticket.application.beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/5/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Alias("order")
public class Order {
    private String id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date orderTime = new Date();
    private Double price;
    private String username;
    private Flight flight;
}
