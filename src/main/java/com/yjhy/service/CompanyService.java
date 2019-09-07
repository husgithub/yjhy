package com.yjhy.service;

import com.yjhy.common.Pager;
import com.yjhy.entity.Company;

import java.util.List;

/**
 * Created by husong on 2018/4/6.
 */
public interface CompanyService {

    Company getCompanyById(Integer companyId);

    Integer countCompany(Company company);

    List<Company> listCompany(Company company, Pager pager);

    void addCompany(Company company);

    void editCompany(Company company);

    void delCompany(Integer id);

    List<Company> selectByIds(String ids);
}
