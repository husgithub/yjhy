package com.yjhy.test.mapper;

import com.yjhy.entity.Company;
import com.yjhy.mapper.CompanyMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by husong on 2018/4/6.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanyMapperTest {

    @Autowired
    private CompanyMapper companyMapper;

    @Test
    public void listCompany(){
        List<Company> list = companyMapper.listCompany(null,null);
        list.forEach(n-> System.out.println(n.getcName()));
    }
}
