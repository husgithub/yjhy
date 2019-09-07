package com.yjhy.mapper;

import com.yjhy.entity.SysAccount;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysAccountMapper {
    List<SysAccount> selectWithAnd(SysAccount record);
}