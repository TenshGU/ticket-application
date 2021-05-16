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
@Alias("company")
public class Company {
    private String name;
    private String location;
}
