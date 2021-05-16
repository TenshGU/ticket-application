package cn.edu.scau.ticket.application.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/5/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Alias("plane")
public class Plane {
    private Integer id;
    private String name;
    private Integer capacity;
    private Company company;
}
