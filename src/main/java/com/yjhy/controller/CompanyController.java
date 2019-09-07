package com.yjhy.controller;

import com.yjhy.common.Pager;
import com.yjhy.entity.Company;
import com.yjhy.entity.common.ResponseBean;
import com.yjhy.service.CompanyService;
import com.yjhy.util.export.CompanyExport;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by husong on 2018/4/6.
 */

@Controller
@RequestMapping("company")
public class CompanyController extends BaseController {

    private static final Logger LOGGER = Logger.getLogger(CompanyController.class);

    @Autowired
    private CompanyService companyService;

    @RequestMapping("list")
    public String list() {
        return "company/list";
    }

    @RequestMapping("getLists")
    @ResponseBody
    public Map<String, Object> listCompany(Company company, Integer page, Integer limit) {
        Map<String, Object> map = new HashMap<String, Object>();
        Integer total = companyService.countCompany(company);
        Pager pager = Pager.buildWithPage(page, limit, total);
        List<Company> list = companyService.listCompany(company, pager);
        map.put("code", 0);
        map.put("error", "");
        map.put("count", total);
        map.put("data", list);
        return map;
    }

    @RequestMapping("getCompanyById")
    @ResponseBody
    public ResponseBean getCompanyById(Integer companyId) {
        ResponseBean responseBean = new ResponseBean();
        try {
            Company company = companyService.getCompanyById(companyId);
            responseBean.setData(company);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("查询公司信息失败！");
            responseBean.setError("查询公司信息失败");
        }
        return responseBean;
    }

    @RequestMapping("toAdd")
    public String toAdd() {
        return "company/add";
    }

    @PostMapping("addSave")
    @ResponseBody
    public ResponseBean addSave(Company company) {
        ResponseBean responseBean = new ResponseBean();
        try {
            companyService.addCompany(company);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            responseBean.setError("新增公司信息异常！");
        }
        return responseBean;
    }

    @RequestMapping("toEdit")
    public String toEdit(Map<String, Object> map, Integer id) {
        map.put("isEdit", true);
        Company company = companyService.getCompanyById(id);
        map.put("c", company);
        return "company/add";
    }

    @PostMapping("editSave")
    @ResponseBody
    public ResponseBean editSave(Company company) {
        ResponseBean responseBean = new ResponseBean();
        try {
            if (!ObjectUtils.isEmpty(company.getId())) {
                companyService.editCompany(company);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            responseBean.setError("修改公司信息异常！");
        }
        return responseBean;
    }

    @PostMapping("delCompany")
    @ResponseBody
    public ResponseBean delCompany(Integer id) {
        ResponseBean responseBean = new ResponseBean();
        try {
            if (!ObjectUtils.isEmpty(id)) {
                companyService.delCompany(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            responseBean.setError("删除公司信息异常！");
        }
        return responseBean;
    }

    //导出
    @RequestMapping("/export")
    public void export(HttpServletRequest request, HttpServletResponse response, String ids) {
        List<Company> list = null;
        String fileName = null;
        OutputStream out = null;
        try {
            list = companyService.selectByIds(ids);
            if (list == null || list.size() <= 0) {
                return;
            }
            response.setContentType("application/vnd.ms-excel");
            fileName = java.net.URLEncoder.encode("公司信息", "UTF-8");
            response.setHeader("content-disposition", "attachment;filename=" + fileName + ".xls");
            out = response.getOutputStream();
            CompanyExport companyExport = new CompanyExport(list);
            companyExport.exportCompanies(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
