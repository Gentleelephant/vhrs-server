package com.zhang.utils;

import com.zhang.model.*;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:Zpg
 * @Date:2020/5/22 15:10
 * @Version:1.0
 * @Description:
 */
public class POIUtils {

    public static ResponseEntity<byte[]> employee2Excel(List<Employee> list) throws IOException {

        // 1. 创建一个Excel文档
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 2. 创建文档摘要
        workbook.createInformationProperties();
        // 3. 获取并配置文档信息
        DocumentSummaryInformation documentSummaryInformation = workbook.getDocumentSummaryInformation();
        // 文档类别
        documentSummaryInformation.setCategory("员工信息表");
        // 文档管理员
        documentSummaryInformation.setManager("Aboy");
        // 公司
        documentSummaryInformation.setCompany("www.codezp.club");
        // 4. 获取文档摘要信息
        SummaryInformation summaryInformation = workbook.getSummaryInformation();
        // 设置文档标题
        summaryInformation.setTitle("员工信息表");
        // 设置文档作者
        summaryInformation.setAuthor("Aboy");
        // 备注信息
        summaryInformation.setComments("文档由Aboy提供");

        // 5. 创建样式
        // 创建标题行的样式
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillBackgroundColor(IndexedColors.YELLOW.index);
//        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        HSSFCellStyle dateCellStyle = workbook.createCellStyle();
        // 设置日期格式
        dateCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
        // 创建一张表单
        HSSFSheet sheet = workbook.createSheet("员工信息表");



        // 设置列的宽度
        sheet.setColumnWidth(0, 5*256);
        sheet.setColumnWidth(1, 12*256);
        sheet.setColumnWidth(2, 10*256);
        sheet.setColumnWidth(3, 5*256);
        sheet.setColumnWidth(4, 12*256);
        sheet.setColumnWidth(5, 20*256);
        sheet.setColumnWidth(6, 10*256);
        sheet.setColumnWidth(7, 10*256);
        sheet.setColumnWidth(8, 16*256);
        sheet.setColumnWidth(9, 12*256);
        sheet.setColumnWidth(10, 15*256);
        sheet.setColumnWidth(11, 20*256);
        sheet.setColumnWidth(12, 16*256);
        sheet.setColumnWidth(13, 14*256);
        sheet.setColumnWidth(14, 14*256);
        sheet.setColumnWidth(15, 12*256);
        sheet.setColumnWidth(16, 8*256);
        sheet.setColumnWidth(17, 20*256);
        sheet.setColumnWidth(18, 20*256);
        sheet.setColumnWidth(19, 12*256);
        sheet.setColumnWidth(20, 15*256);
        sheet.setColumnWidth(21, 15*256);
        sheet.setColumnWidth(22, 15*256);
        sheet.setColumnWidth(23, 15*256);
        sheet.setColumnWidth(24, 15*256);
        // 6. 创建标题行
        HSSFRow row0 = sheet.createRow(0);
        HSSFCell cell0 = row0.createCell(0);
        cell0.setCellValue("编号");
        cell0.setCellStyle(headerStyle);

        HSSFCell cell1 = row0.createCell(1);
        cell1.setCellStyle(headerStyle);
        cell1.setCellValue("姓名");
        HSSFCell cell2 = row0.createCell(2);
        cell2.setCellStyle(headerStyle);
        cell2.setCellValue("工号");
        HSSFCell cell3 = row0.createCell(3);
        cell3.setCellStyle(headerStyle);
        cell3.setCellValue("性别");
        HSSFCell cell4 = row0.createCell(4);
        cell4.setCellStyle(headerStyle);
        cell4.setCellValue("出生日期");
        HSSFCell cell5 = row0.createCell(5);
        cell5.setCellStyle(headerStyle);
        cell5.setCellValue("身份证号");
        HSSFCell cell6 = row0.createCell(6);
        cell6.setCellStyle(headerStyle);
        cell6.setCellValue("婚姻状况");
        HSSFCell cell7 = row0.createCell(7);
        cell7.setCellStyle(headerStyle);
        cell7.setCellValue("民族");
        HSSFCell cell8 = row0.createCell(8);
        cell8.setCellStyle(headerStyle);
        cell8.setCellValue("籍贯");
        HSSFCell cell9 = row0.createCell(9);
        cell9.setCellStyle(headerStyle);
        cell9.setCellValue("政治面貌");
        HSSFCell cell10 = row0.createCell(10);
        cell10.setCellStyle(headerStyle);
        cell10.setCellValue("电子邮件");
        HSSFCell cell11 = row0.createCell(11);
        cell11.setCellStyle(headerStyle);
        cell11.setCellValue("电话号码");
        HSSFCell cell12 = row0.createCell(12);
        cell12.setCellStyle(headerStyle);
        cell12.setCellValue("联系地址");
        HSSFCell cell13 = row0.createCell(13);
        cell13.setCellStyle(headerStyle);
        cell13.setCellValue("所属部门");
        HSSFCell cell14 = row0.createCell(14);
        cell14.setCellStyle(headerStyle);
        cell14.setCellValue("职称");
        HSSFCell cell15 = row0.createCell(15);
        cell15.setCellValue("职位");
        cell15.setCellStyle(headerStyle);
        HSSFCell cell16 = row0.createCell(16);
        cell16.setCellStyle(headerStyle);
        cell16.setCellValue("聘用形式");
        HSSFCell cell17 = row0.createCell(17);
        cell17.setCellStyle(headerStyle);
        cell17.setCellValue("最高学历");
        HSSFCell cell18 = row0.createCell(18);
        cell18.setCellStyle(headerStyle);
        cell18.setCellValue("专业");
        HSSFCell cell19 = row0.createCell(19);
        cell19.setCellStyle(headerStyle);
        cell19.setCellValue("毕业院校");
        HSSFCell cell20 = row0.createCell(20);
        cell20.setCellStyle(headerStyle);
        cell20.setCellValue("入职日期");
        HSSFCell cell21 = row0.createCell(21);
        cell21.setCellStyle(headerStyle);
        cell21.setCellValue("转正日期");
        HSSFCell cell22 = row0.createCell(22);
        cell22.setCellStyle(headerStyle);
        cell22.setCellValue("合同起始日期");
        HSSFCell cell23 = row0.createCell(23);
        cell23.setCellStyle(headerStyle);
        cell23.setCellValue("合同截至日期");
        HSSFCell cell24 = row0.createCell(24);
        cell24.setCellStyle(headerStyle);
        cell24.setCellValue("合同期限");

        for (int i = 0; i < list.size(); i++) {
            Employee emp = list.get(i);
            HSSFRow row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(emp.getId());
            row.createCell(1).setCellValue(emp.getName());
            row.createCell(2).setCellValue(emp.getWorkID());
            row.createCell(3).setCellValue(emp.getGender());
//            row.createCell(4).setCellValue(emp.getBirthday());
            HSSFCell c4 = row.createCell(4);
            c4.setCellStyle(dateCellStyle);
            c4.setCellValue(emp.getBirthday());
            row.createCell(5).setCellValue(emp.getIdCard());
            row.createCell(6).setCellValue(emp.getWedlock());
            row.createCell(7).setCellValue(emp.getNation().getName());
            row.createCell(8).setCellValue(emp.getNativePlace());
            row.createCell(9).setCellValue(emp.getPoliticsstatus().getName());
            row.createCell(10).setCellValue(emp.getEmail());
            row.createCell(11).setCellValue(emp.getPhone());
            row.createCell(12).setCellValue(emp.getAddress());
            row.createCell(13).setCellValue(emp.getDepartment().getName());
            row.createCell(14).setCellValue(emp.getJobLevel().getName());
            row.createCell(15).setCellValue(emp.getPosition().getName());
            row.createCell(16).setCellValue(emp.getEngageForm());
            row.createCell(17).setCellValue(emp.getTiptopDegree());
            row.createCell(18).setCellValue(emp.getSpecialty());
            row.createCell(19).setCellValue(emp.getSchool());
            HSSFCell c20 = row.createCell(20);
            c20.setCellStyle(dateCellStyle);
            c20.setCellValue(emp.getBeginDate());
//            row.createCell(21).setCellValue(emp.getConversionTime());
            HSSFCell c21 = row.createCell(21);
            c21.setCellStyle(dateCellStyle);
            c21.setCellValue(emp.getBeginDate());
//            row.createCell(22).setCellValue(emp.getBeginContract());
            HSSFCell c22 = row.createCell(22);
            c22.setCellStyle(dateCellStyle);
            c22.setCellValue(emp.getBeginDate());
//            row.createCell(23).setCellValue(emp.getEndContract());
            HSSFCell c23 = row.createCell(23);
            c23.setCellStyle(dateCellStyle);
            c23.setCellValue(emp.getBeginDate());
            row.createCell(24).setCellValue(emp.getContractTerm());
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentDispositionFormData("attachment", new String("员工表.xls".getBytes("UTF-8"),"ISO-8859-1"));
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        workbook.write(byteArrayOutputStream);

        return new ResponseEntity<byte[]>(byteArrayOutputStream.toByteArray(),httpHeaders, HttpStatus.CREATED);
    }

    /**
     * Excel解析成数据集合
     * @param file
     * @param allNations
     * @param allPoliticsstatus
     * @param allDepartment
     * @param allPositions
     * @param allJobLevels
     * @return
     */
    public static List<Employee> excel2Employee(MultipartFile file, List<Nation> allNations,
                                                List<Politicsstatus> allPoliticsstatus, List<Department> allDepartment, List<Position> allPositions, List<JobLevel> allJobLevels) {
        List<Employee> list = new ArrayList<>();
        Employee employee = null;
        try {
            // 1.创建一个workbook对象
            HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());
            // 2.获取workbook中表单的数量
            int numberOfSheets = workbook.getNumberOfSheets();
            for (int i = 0; i < numberOfSheets; i++) {
                // 3.获取表单
                HSSFSheet sheet = workbook.getSheetAt(i);
                // 4.获取表单中的行数
                int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
                for (int j = 0; j < physicalNumberOfRows; j++) {
                    // 5.跳过标题行
                    if (j == 0){
                        continue;
                    }
                    // 6.获取行
                    HSSFRow row = sheet.getRow(j);
                    if (row == null){
                        continue; // 防止数据中间有空行
                    }
                    // 7.获取列数
                    int physicalNumberOfCells = row.getPhysicalNumberOfCells();
                    employee = new Employee();
                    for (int k = 0; k < physicalNumberOfCells; k++) {
                        HSSFCell cell = row.getCell(k);
                        if (cell == null){
                            continue;
                        }
                        switch (cell.getCellType()){
                            case STRING:
                                String stringCellValue = cell.getStringCellValue();
                                switch (k){
                                    case 1:
                                        employee.setName(stringCellValue);
                                        break;
                                    case 2:
                                        employee.setWorkID(stringCellValue);
                                        break;
                                    case 3:
                                        employee.setGender(stringCellValue);
                                        break;
                                    case 5:
                                        employee.setIdCard(stringCellValue);
                                        break;
                                    case 6:
                                        employee.setWedlock(stringCellValue);
                                        break;
                                    case 7:
                                        int nationIndex = allNations.indexOf(new Nation(stringCellValue));
                                        employee.setNationId(allNations.get(nationIndex).getId());
                                        break;
                                    case 8:
                                        employee.setNativePlace(stringCellValue);
                                        break;
                                    case 9:
                                        int politicstatus = allPoliticsstatus.indexOf(new Politicsstatus(stringCellValue));
                                        employee.setPoliticId(allPoliticsstatus.get(politicstatus).getId());
                                        break;
                                    case 10:
                                        employee.setEmail(stringCellValue);
                                        break;
                                    case 11:
                                        employee.setPhone(stringCellValue);
                                        break;
                                    case 12:
                                        employee.setAddress(stringCellValue);
                                        break;
                                    case 13:
                                        int departmentIndex = allDepartment.indexOf(new Department(stringCellValue));
                                        employee.setDepartmentId(allDepartment.get(departmentIndex).getId());
                                        break;
                                    case 14:
                                        int joblevelIndex = allJobLevels.indexOf(new JobLevel(stringCellValue));
                                        employee.setJobLevelId(allJobLevels.get(joblevelIndex).getId());
                                        break;
                                    case 15:
                                        int positionIndex = allPositions.indexOf(new Position(stringCellValue));
                                        employee.setPosId(allPositions.get(positionIndex).getId());
                                        break;
                                    case 16:
                                        employee.setEngageForm(stringCellValue);
                                        break;
                                    case 17:
                                        employee.setTiptopDegree(stringCellValue);
                                        break;
                                    case 18:
                                        employee.setSpecialty(stringCellValue);
                                        break;
                                    case 19:
                                        employee.setSchool(stringCellValue);
                                        break;
                                        default:{

                                        }
                                }
                                break;
                            default: {
                                switch (k){
                                    case 4:
                                        employee.setBirthday(cell.getDateCellValue());
                                        break;
                                    case 20:
                                        employee.setBeginDate(cell.getDateCellValue());
                                        break;
                                    case 21:
                                        employee.setConversionTime(cell.getDateCellValue());
                                        break;
                                    case 22:
                                        employee.setBeginContract(cell.getDateCellValue());
                                        break;
                                    case 23:
                                        employee.setEndContract(cell.getDateCellValue());
                                        break;
                                    case 24:
                                        employee.setContractTerm(cell.getNumericCellValue());
                                        break;
                                        default: {

                                        }
                                }
                                }
                                break;
                        }
                    }
                    list.add(employee);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
