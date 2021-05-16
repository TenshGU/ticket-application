package cn.edu.scau.ticket.application.mapper;

import cn.edu.scau.ticket.application.beans.Company;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CompanyMapper {
    Company getCompanyByName(String name);
}
