package com.yjhy.util.export;


import com.yjhy.entity.Company;
import com.yjhy.entity.LegalPerson;
import com.yjhy.entity.Taxation;
import com.yjhy.entity.enums.LegalPersonEnum;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyExport {

    private List<Company> list;

    private Integer sheetNum = 0;

    public CompanyExport(List<Company> list) {
        super();
        this.list = list;
    }

    public void createSheet(Workbook wb, Company company) {
        HSSFCellStyle cell_style = (HSSFCellStyle) wb.createCellStyle();
        cell_style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cell_style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中

        Sheet sheet = wb.createSheet((sheetNum++) + company.getcName());
        sheet.setDefaultColumnWidth(20);

        Row row_0 = sheet.createRow((short) 0);
        Cell cell_00 = row_0.createCell((short) 0);
        cell_00.setCellValue(FormatValue.format("str", company.getcName()));

        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));

        Row row_1 = sheet.createRow((short) 1);
        Cell cell_10 = row_1.createCell((short) 0);
        cell_10.setCellValue("公司名称");
        Cell cell_11 = row_1.createCell((short) 1);
        cell_11.setCellValue(company.getcName());
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 5));

        Row row_2 = sheet.createRow((short) 2);
        Cell cell_20 = row_2.createCell((short) 0);
        cell_20.setCellValue("地址");
        Cell cell_21 = row_2.createCell((short) 1);
        cell_21.setCellValue(company.getcAddress());
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 1, 2));
        Cell cell_23 = row_2.createCell((short) 3);
        cell_23.setCellValue("联系人");
        Cell cell_24 = row_2.createCell((short) 4);
        cell_24.setCellStyle(cell_style);
        cell_24.setCellValue(company.getcContacts());
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 4, 5));

        Row row_3 = sheet.createRow((short) 3);
        Cell cell_30 = row_3.createCell((short) 0);
        cell_30.setCellValue("注册资本（万）	");
        Cell cell_31 = row_3.createCell((short) 1);
        cell_31.setCellValue(FormatValue.format("str", company.getcCapital()));
        Cell cell_32 = row_3.createCell((short) 2);
        cell_32.setCellValue("公司类型");
        Cell cell_33 = row_3.createCell((short) 3);
        cell_33.setCellValue(FormatValue.format("str", company.getcType()));
        Cell cell_34 = row_3.createCell((short) 4);
        cell_34.setCellValue("公司规模");
        Cell cell_35 = row_3.createCell((short) 5);
        cell_35.setCellValue(FormatValue.format("guimo", company.getcSize()));

        Row row_4 = sheet.createRow((short) 4);
        Cell cell_40 = row_4.createCell((short) 0);
        cell_40.setCellValue("客户来源");
        Cell cell_41 = row_4.createCell((short) 1);
        cell_41.setCellValue(FormatValue.format("str", company.getcCusource()));
        Cell cell_42 = row_4.createCell((short) 2);
        cell_42.setCellValue("推荐人");
        Cell cell_43 = row_4.createCell((short) 3);
        cell_43.setCellValue(company.getcReferrer());
        Cell cell_44 = row_4.createCell((short) 4);
        cell_44.setCellValue("邮箱");
        Cell cell_45 = row_4.createCell((short) 5);
        cell_45.setCellValue(company.getcEmail());

        Row row_5 = sheet.createRow((short) 5);
        Cell cell_50 = row_5.createCell((short) 0);
        cell_50.setCellValue("经营范围");
        Cell cell_51 = row_5.createCell((short) 1);
        cell_51.setCellValue(company.getcScope());
        sheet.addMergedRegion(new CellRangeAddress(5, 5, 1, 5));

        Row row_6 = sheet.createRow((short) 6);
        Cell cell_60 = row_6.createCell((short) 0);
        cell_60.setCellValue("法人/股东");
        Cell cell_61 = row_6.createCell((short) 1);
        cell_61.setCellValue("姓名");
        Cell cell_62 = row_6.createCell((short) 2);
        cell_62.setCellValue("身份证号码");
        Cell cell_63 = row_6.createCell((short) 3);
        cell_63.setCellValue("股权分配（%）");
        Cell cell_64 = row_6.createCell((short) 4);
        cell_64.setCellValue("职务");
        sheet.addMergedRegion(new CellRangeAddress(6, 6, 4, 5));
        //法人
        int num = 7;
        List<LegalPerson> legals = company.getLegalPersons();
        if (legals == null || legals.size() <= 0) {
            Row row_7 = sheet.createRow((short) num);
            Cell cell_70 = row_7.createCell((short) 0);
            cell_70.setCellValue("无");
            sheet.addMergedRegion(new CellRangeAddress(num, num, 0, 5));
            num++;
        } else {
            for (int i = 0; i < legals.size(); i++) {
                LegalPerson lp = legals.get(i);
                Row lp_row0 = sheet.createRow((short) num);
                Cell lp_cell_00 = lp_row0.createCell((short) 0);
                lp_cell_00.setCellValue(LegalPersonEnum.getNameByCode(lp.getlType()));
                Cell lp_cell_01 = lp_row0.createCell((short) 1);
                lp_cell_01.setCellValue(lp.getlName());
                Cell lp_cell_02 = lp_row0.createCell((short) 2);
                lp_cell_02.setCellValue(lp.getlIdcard());
                Cell lp_cell_03 = lp_row0.createCell((short) 3);
                lp_cell_03.setCellValue(FormatValue.format("str", lp.getlAllocation()));
                Cell lp_cell_04 = lp_row0.createCell((short) 4);
                lp_cell_04.setCellValue(lp.getlJob());
                sheet.addMergedRegion(new CellRangeAddress(num, num, 4, 5));
                num++;
            }
        }

        //经办人
        Row row_8 = sheet.createRow((short) num);
        Cell cell_80 = row_8.createCell((short) 0);
        cell_80.setCellValue("经办人");
        Cell cell_81 = row_8.createCell((short) 1);
        cell_81.setCellValue(company.getcOperator());
        sheet.addMergedRegion(new CellRangeAddress(num, num, 1, 2));
        Cell cell_83 = row_8.createCell((short) 3);
        cell_83.setCellValue("联系方式");
        Cell cell_84 = row_8.createCell((short) 4);
        cell_84.setCellStyle(cell_style);
        cell_84.setCellValue(company.getcOperatorphone());
        sheet.addMergedRegion(new CellRangeAddress(num, num, 4, 5));
        num++;

        //营业执照
        Row row_9 = sheet.createRow((short) num);
        Cell cell_90 = row_9.createCell((short) 0);
        cell_90.setCellValue("营业执照");
        Cell cell_91 = row_9.createCell((short) 1);
        cell_91.setCellValue("注册日期");
        Cell cell_92 = row_9.createCell((short) 2);
        cell_92.setCellValue(FormatValue.format("time", company.getcRegtime()));
        Cell cell_93 = row_9.createCell((short) 3);
        cell_93.setCellValue("统一信用代码");
        Cell cell_94 = row_9.createCell((short) 4);
        cell_94.setCellValue(company.getcRegcode());
        sheet.addMergedRegion(new CellRangeAddress(num, num, 4, 5));
        num++;

        Row row_10 = sheet.createRow((short) num);
        Cell cell_101 = row_10.createCell((short) 1);
        cell_101.setCellValue("营业期限");
        Cell cell_102 = row_10.createCell((short) 2);
        cell_102.setCellStyle(cell_style);
        cell_102.setCellValue(FormatValue.format("time", company.getBnStart()) + " 至 " + FormatValue.format("time", company.getBnEnd()));
        sheet.addMergedRegion(new CellRangeAddress(num, num, 2, 5));
        sheet.addMergedRegion(new CellRangeAddress(num - 1, num, 0, 0));
        num++;

        Taxation ds = null;
        Taxation gs = null;
        List<Taxation> taxations = company.getTaxations();
        if (taxations != null) {
            for (int i = 0; i < taxations.size(); i++) {
                Taxation single = taxations.get(i);
                if ("ds".equals(single.gettType())) {
                    ds = single;
                } else if ("gs".equals(single.gettType())) {
                    gs = single;
                }
            }
        }

        //地税
        if (ds != null) {
            Row row_11 = sheet.createRow((short) num);
            Cell cell_110 = row_11.createCell((short) 0);
            cell_110.setCellValue("地税");
            Cell cell_111 = row_11.createCell((short) 1);
            cell_111.setCellValue("地税专管员");
            Cell cell_112 = row_11.createCell((short) 2);
            cell_112.setCellValue("姓名");
            Cell cell_113 = row_11.createCell((short) 3);
            cell_113.setCellValue(FormatValue.format("str", ds.gettName()));
            Cell cell_114 = row_11.createCell((short) 4);
            cell_114.setCellValue("联系方式	");
            Cell cell_115 = row_11.createCell((short) 5);
            cell_115.setCellValue(ds.gettPhone());
            num++;

            Row row_12 = sheet.createRow((short) num);
            Cell cell_121 = row_12.createCell((short) 1);
            cell_121.setCellValue("地税税源地址");
            Cell cell_122 = row_12.createCell((short) 2);
            cell_122.setCellStyle(cell_style);
            cell_122.setCellValue(ds.gettAddress());
            sheet.addMergedRegion(new CellRangeAddress(num, num, 2, 5));
            num++;

            Row row_13 = sheet.createRow((short) num);
            Cell cell_131 = row_13.createCell((short) 1);
            cell_131.setCellValue("税种核定日期");
            Cell cell_132 = row_13.createCell((short) 2);
            cell_132.setCellValue(FormatValue.format("time", ds.gettChecktime()));
            Cell cell_133 = row_13.createCell((short) 3);
            cell_133.setCellValue("税种");
            Cell cell_134 = row_13.createCell((short) 4);
            cell_134.setCellValue(ds.gettVariety());
            sheet.addMergedRegion(new CellRangeAddress(num, num, 4, 5));
            num++;

            Row row_14 = sheet.createRow((short) num);
            Cell cell_141 = row_14.createCell((short) 1);
            cell_141.setCellValue("申报(月报/季报)");
            Cell cell_142 = row_14.createCell((short) 2);
            cell_142.setCellValue(FormatValue.format("sb", ds.gettShenbao()));
            Cell cell_143 = row_14.createCell((short) 3);
            cell_143.setCellValue("会计电话	");
            Cell cell_144 = row_14.createCell((short) 4);
            cell_144.setCellValue(ds.gettAccountantphone());
            sheet.addMergedRegion(new CellRangeAddress(num, num, 4, 5));
            sheet.addMergedRegion(new CellRangeAddress(num - 3, num, 0, 0));
            num++;
        }

        //国税
        if (gs != null) {
            Row row_15 = sheet.createRow((short) num);
            Cell cell_150 = row_15.createCell((short) 0);
            cell_150.setCellValue("国税");
            Cell cell_151 = row_15.createCell((short) 1);
            cell_151.setCellValue("国税专管员");
            Cell cell_152 = row_15.createCell((short) 2);
            cell_152.setCellValue("姓名");
            Cell cell_153 = row_15.createCell((short) 3);
            cell_153.setCellValue(gs.gettName());
            Cell cell_154 = row_15.createCell((short) 4);
            cell_154.setCellValue("联系方式	");
            Cell cell_155 = row_15.createCell((short) 5);
            cell_155.setCellValue(gs.gettPhone());
            num++;

            Row row_16 = sheet.createRow((short) num);
            Cell cell_161 = row_16.createCell((short) 1);
            cell_161.setCellValue("国税税源地址");
            Cell cell_162 = row_16.createCell((short) 2);
            cell_162.setCellStyle(cell_style);
            cell_162.setCellValue(gs.gettAddress());
            sheet.addMergedRegion(new CellRangeAddress(num, num, 2, 5));
            num++;

            Row row_17 = sheet.createRow((short) num);
            Cell cell_171 = row_17.createCell((short) 1);
            cell_171.setCellValue("税种核定日期");
            Cell cell_172 = row_17.createCell((short) 2);
            cell_172.setCellValue(FormatValue.format("time", gs.gettChecktime()));
            Cell cell_173 = row_17.createCell((short) 3);
            cell_173.setCellValue("税种");
            Cell cell_174 = row_17.createCell((short) 4);
            cell_174.setCellValue(gs.gettVariety());
            sheet.addMergedRegion(new CellRangeAddress(num, num, 4, 5));
            num++;

            Row row_18 = sheet.createRow((short) num);
            Cell cell_181 = row_18.createCell((short) 1);
            cell_181.setCellValue("申报(月报/季报)");
            Cell cell_182 = row_18.createCell((short) 2);
            cell_182.setCellValue(FormatValue.format("sb", gs.gettShenbao()));
            Cell cell_183 = row_18.createCell((short) 3);
            cell_183.setCellValue("会计电话	");
            Cell cell_184 = row_18.createCell((short) 4);
            cell_184.setCellValue(gs.gettAccountantphone());
            sheet.addMergedRegion(new CellRangeAddress(num, num, 4, 5));
            sheet.addMergedRegion(new CellRangeAddress(num - 3, num, 0, 0));
            num++;
        }


        //地税钥匙/国税钥匙/金税盘
        Row row_19 = sheet.createRow((short) num);
        Cell cell_190 = row_19.createCell((short) 0);
        cell_190.setCellValue("地税钥匙/国税钥匙/金税盘");
        Cell cell_191 = row_19.createCell((short) 1);
        cell_191.setCellValue(FormatValue.format("shui", company.getcTaxationtype()));
        sheet.addMergedRegion(new CellRangeAddress(num, num, 1, 5));
        num++;

        //对公账号
        Row row_20 = sheet.createRow((short) num);
        Cell cell_200 = row_20.createCell((short) 0);
        cell_200.setCellValue("对公账号");
        Cell cell_201 = row_20.createCell((short) 1);
        cell_201.setCellValue(FormatValue.format("str", company.getcPublicaccount()));
        sheet.addMergedRegion(new CellRangeAddress(num, num, 1, 5));
        num++;

        //是否已建账
        Row row_21 = sheet.createRow((short) num);
        Cell cell_210 = row_21.createCell((short) 0);
        cell_210.setCellValue("是否已建账");
        Cell cell_211 = row_21.createCell((short) 1);
        cell_211.setCellValue(FormatValue.format("yes", company.getcIsjianz()));
        Cell cell_212 = row_21.createCell((short) 2);
        cell_212.setCellValue("是否上报会计");
        Cell cell_213 = row_21.createCell((short) 3);
        cell_213.setCellValue(FormatValue.format("yes", company.getcIsreported()));
        Cell cell_214 = row_21.createCell((short) 4);
        cell_214.setCellValue("会计姓名");
        Cell cell_215 = row_21.createCell((short) 5);
        cell_215.setCellValue(FormatValue.format("str", company.getcAccountantname()));
        num++;

        //变更
        Row row_22 = sheet.createRow((short) num);
        Cell cell_220 = row_22.createCell((short) 0);
        cell_220.setCellValue("变更");
        Cell cell_221 = row_22.createCell((short) 1);
        cell_221.setCellValue(FormatValue.format("str", company.getcUpdate()));
        sheet.addMergedRegion(new CellRangeAddress(num, num, 1, 5));
        num++;

        //备注
        Row row_23 = sheet.createRow((short) num);
        Cell cell_230 = row_23.createCell((short) 0);
        cell_230.setCellValue("备注");
        Cell cell_231 = row_23.createCell((short) 1);
        cell_231.setCellValue(FormatValue.format("str", company.getcNotes()));
        sheet.addMergedRegion(new CellRangeAddress(num, num, 1, 5));
        num++;

        for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
            Row row = sheet.getRow(i);
            row.setHeightInPoints(20);
            for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
                Cell cell = row.getCell(j);
                if (cell != null) {
                    cell.setCellStyle(cell_style);
                }

            }
        }

    }

    public void exportCompanies(OutputStream out) {
        try {
            Workbook wb = new HSSFWorkbook();
            if (list == null || list.size() <= 0) {
                return;
            }
            for (int i = 0; i < list.size(); i++) {
                Company company = list.get(i);
                createSheet(wb, company);
            }
            wb.write(out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            List<Company> list = new ArrayList<Company>();
            for (int i = 0; i < 5; i++) {
                LegalPerson legalPerson = new LegalPerson();
                legalPerson.setlName("AFS");
                List<LegalPerson> lps = new ArrayList<LegalPerson>();
                lps.add(legalPerson);
                Company e = new Company();
                e.setcName("公司" + i);
                e.setcContacts("联系人" + i);
                e.setLegalPersons(lps);
                list.add(e);
            }
            String path = "C:/Users/husong/Desktop/新建文件夹/workbook.xls";
            File file = new File(path);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            FileOutputStream out = new FileOutputStream(file);
            CompanyExport companyExport = new CompanyExport(list);
            companyExport.exportCompanies(out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
