package com.maleic.demo.utils.excel;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;

public interface ExcelFormat {

    //制作excel的用户id
    int userid = 0;

    //制作时间
    Date time = new Date();

    //获取excel文件名
    public String getFileName();

    //制作excel
    public String make();

}
