package com.yjhy.mapper;

import com.yjhy.common.Pager;
import com.yjhy.entity.Company;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CompanyMapper {

    Company getCompanyById(@Param("id") Integer id);

    List<Company> listCompany(@Param("company") Company company, @Param("pager") Pager pager);

    Integer countCompany(@Param("company") Company company);

    Integer addCompany(Company company);

    Integer editCompanySelective(Company company);

    Integer delOtherData(@Param("id") Integer id);

    Integer delCompany(@Param("id") Integer id);

    List<Company> selectByIds(@Param("ids") List<String> ids);
}