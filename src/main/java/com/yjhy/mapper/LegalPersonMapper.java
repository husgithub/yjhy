package com.yjhy.mapper;

import com.yjhy.entity.LegalPerson;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LegalPersonMapper {

    List<LegalPerson> getLegalPersonByCompanyId(@Param("companyId") Integer companyId);

    Integer addLegalPersons(@Param("legalPersonList") List<LegalPerson> legalPersonList);

}