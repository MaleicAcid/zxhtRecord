package com.maleic.demo.service.excel;

import org.apache.poi.hssf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;

public class WeekRecord implements ExcelFormat {

    @Override
    public String getFileName() {
        return "d:\\" + "2017-12-4 "+ "weekRecord.xls";
    }
    @Override
    public String make () {

        String filePath= this.getFileName();//文件路径
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("周工作记录表");

        createTitle(workbook, sheet);

        try {
            FileOutputStream out = new FileOutputStream(filePath);
            workbook.write(out);//保存Excel文件
            out.close();//关闭文件流
            return filePath;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    public void createTitle(HSSFWorkbook workbook, HSSFSheet sheet) {
        HSSFRow row = sheet.createRow(0);
        //设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
        sheet.setColumnWidth(2, 12*256);
        sheet.setColumnWidth(3, 17*256);

        //设置为居中加粗
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setFont(font);

        HSSFCell cell;
        cell = row.createCell(0);
        cell.setCellValue("序号");
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue("金额");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("描述");
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue("日期");
        cell.setCellStyle(style);


    }
}
