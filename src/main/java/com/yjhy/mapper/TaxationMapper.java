package com.yjhy.mapper;

import com.yjhy.entity.Taxation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TaxationMapper {

    List<Taxation> getTaxationByCompanyId(@Param("companyId") Integer companyId);

    Integer addTaxations(@Param("taxationList") List<Taxation> taxationList);
}