package com.maleic.demo.service.excel;

import org.apache.poi.hssf.usermodel.*;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelService {

    List<ExcelFormat> excels = new ArrayList<ExcelFormat>();

    List<String> result = new ArrayList<String>();

    public void addExcel(ExcelFormat excel)
    {
        this.excels.add(excel);
    }

    public List<String> doMake()
    {
        for (ExcelFormat excel:excels){
            this.result.add(excel.make());
        }

        return this.result;
    }
}
