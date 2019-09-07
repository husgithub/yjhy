package com.yjhy.service.impl;

import com.yjhy.common.Pager;
import com.yjhy.entity.Company;
import com.yjhy.entity.LegalPerson;
import com.yjhy.entity.Taxation;
import com.yjhy.mapper.CompanyMapper;
import com.yjhy.mapper.LegalPersonMapper;
import com.yjhy.mapper.TaxationMapper;
import com.yjhy.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by husong on 2018/4/6.
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private LegalPersonMapper legalPersonMapper;

    @Autowired
    private TaxationMapper taxationMapper;

    @Override
    public Company getCompanyById(Integer companyId) {
        Company company = null;
        if (!ObjectUtils.isEmpty(companyId)) {
            company = companyMapper.getCompanyById(companyId);
            company.setLegalPersons(legalPersonMapper.getLegalPersonByCompanyId(companyId));
            company.setTaxations(taxationMapper.getTaxationByCompanyId(companyId));
        }
        return company;
    }

    @Override
    public Integer countCompany(Company company) {
        return companyMapper.countCompany(company);
    }

    @Override
    public List<Company> listCompany(Company company, Pager pager) {
        if (pager == null) {
            pager = Pager.buildWithStart(0, 10, 10);
        }
        return companyMapper.listCompany(company, pager);
    }

    @Transactional
    @Override
    public void addCompany(Company company) {
        company.setcCreatetime(new Date());
        company.setcModification(new Date());
        company.setcStatus("1");
        companyMapper.addCompany(company);
        Integer companyId = company.getId();
        for (LegalPerson legalPerson : company.getLegalPersons()) {
            legalPerson.setCompanyId(companyId);
        }
        for (Taxation taxation : company.getTaxations()) {
            taxation.settCompanyid(companyId);
        }
        legalPersonMapper.addLegalPersons(company.getLegalPersons());
        taxationMapper.addTaxations(company.getTaxations());
    }

    @Transactional
    @Override
    public void editCompany(Company company) {
        companyMapper.delOtherData(company.getId());
        company.setcModification(new Date());
        companyMapper.editCompanySelective(company);
        Integer companyId = company.getId();
        for (LegalPerson legalPerson : company.getLegalPersons()) {
            legalPerson.setCompanyId(companyId);
        }
        for (Taxation taxation : company.getTaxations()) {
            taxation.settCompanyid(companyId);
        }
        legalPersonMapper.addLegalPersons(company.getLegalPersons());
        taxationMapper.addTaxations(company.getTaxations());
    }

    @Transactional
    @Override
    public void delCompany(Integer id) {
        companyMapper.delCompany(id);
    }

    @Override
    public List<Company> selectByIds(String ids) {
        List<String> list = null;
        if (!ObjectUtils.isEmpty(ids)) {
            list = Arrays.asList(ids.split(","));
        }
        List<Company> result = companyMapper.selectByIds(list);
        Iterator<Company> iterator = result.iterator();
        while (iterator.hasNext()) {
            Company single = iterator.next();
            int companyId = single.getId();
            single.setTaxations(taxationMapper.getTaxationByCompanyId(companyId));
            single.setLegalPersons(legalPersonMapper.getLegalPersonByCompanyId(companyId));
        }
        return result;
    }
}
