package com.maleic.demo.utils.excel;

import com.maleic.demo.domain.Record;
import com.maleic.demo.domain.RecordRepository;
import com.maleic.demo.utils.TimeUtil;
import com.maleic.demo.utils.excel.ExcelFormat;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Component
public class WeekRecord implements ExcelFormat {

    @Autowired
    private RecordRepository recordRepository;

    private HashMap<Integer, String> title = new HashMap<Integer, String>();

    public  WeekRecord(){
//        WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
//        recordRepository = (RecordRepository)wac .getBean("RecordRepository");
    }

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
        createContent(workbook, sheet);

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
        sheet.setColumnWidth(2, 16*256);
        sheet.setColumnWidth(3, 64*256);

        //设置为左对齐 宋体11号
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 11);
        font.setFontName("宋体");
        style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        style.setFont(font);

        title.put(0,"日期");
        title.put(1,"小时数");
        title.put(2,"时间段");
        title.put(3,"说明");

        HSSFCell cell;
        Iterator it = title.keySet().iterator();
        while(it.hasNext()) {
            Integer key = (Integer)it.next();

            cell = row.createCell(key);
            cell.setCellValue(title.get(key));
            cell.setCellStyle(style);
        }

    }

    public void createContent(HSSFWorkbook workbook, HSSFSheet sheet) {
        //设置为左对齐 宋体11号
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 11);
        font.setFontName("宋体");
        style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        style.setFont(font);

        List<String> days = TimeUtil.getWeekdays();

        int rowNum = 1;
        for(String day:days) {
            List<Record> records = recordRepository.findByDid(new Integer(day));
            boolean flag = true;
            if (records == null){
                continue;
            }
            for(Record record:records) {
                HSSFRow row = sheet.createRow(rowNum);
                if (flag == true) {
                    row.createCell(0).setCellValue(record.getDid());
                    flag = false;
                }
                row.createCell(1).setCellValue(record.calHours());
                row.createCell(2).setCellValue(record.getStartTime() + "~" + record.getEndTime());

                HSSFCell cell = row.createCell(3);
                cell.setCellValue(record.getExplain());
                cell.setCellStyle(style);
                rowNum++;
            }

        }

        //设置日期格式
//        HSSFCellStyle style=workbook.createCellStyle();
//        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
//
//
//        //拼装blobName
//        String fileName = "测试数据统计表.xlsx";
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
//        String dateTime = dateFormat.format(new Date());
//        String blobName =  dateTime + "/" + UUID.randomUUID().toString().replaceAll("-", "") + "/" + fileName;
    }
}
