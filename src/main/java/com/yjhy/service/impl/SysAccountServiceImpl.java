package com.yjhy.service.impl;

import com.yjhy.entity.SysAccount;
import com.yjhy.mapper.SysAccountMapper;
import com.yjhy.service.SysAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SysAccountServiceImpl implements SysAccountService {

    @Autowired
    private SysAccountMapper sysAccountMapper;

    @Override
    public SysAccount login(SysAccount sysAccount) {
        List<SysAccount> list = sysAccountMapper.selectWithAnd(sysAccount);
        if (list == null || list.size() <= 0) {
            return null;
        }
        return list.get(0);
    }

}
