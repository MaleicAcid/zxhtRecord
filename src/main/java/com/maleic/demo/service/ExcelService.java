package com.maleic.demo.service;

import com.maleic.demo.utils.excel.ExcelFormat;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelService {

    private List<ExcelFormat> excels = new ArrayList<ExcelFormat>();

    private List<String> result = new ArrayList<String>();

    public void addExcel(ExcelFormat excel)
    {
        excels.add(excel);
    }

    public List<String> doMake()
    {
        for (ExcelFormat excel:excels){
            result.add(excel.make());
        }

        return this.result;
    }
}
