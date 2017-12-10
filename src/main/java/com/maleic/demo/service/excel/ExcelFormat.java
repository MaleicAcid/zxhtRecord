package com.maleic.demo.service.excel;

import java.util.Date;

//excel模板
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
