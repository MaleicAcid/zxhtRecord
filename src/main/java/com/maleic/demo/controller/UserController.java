package com.maleic.demo.controller;

import com.maleic.demo.service.MailService;
import com.maleic.demo.service.excel.ExcelService;
import com.maleic.demo.service.excel.WeekRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private MailService mailService;

    @RequestMapping("/sendMail")
    public ModelAndView sendMail(){
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("message", "这是测试的内容。。。");
        model.put("toUserName", "张三");
        model.put("fromUserName", "老许");
        String content = "你好,这是马来酸的2017-12-4日为起始周的周工作记录。";
        content += "<br/>";
        content += "如果下载附件后提示无法打开，可尝试如下操作:右键文件->属性->解除锁定";
        content += "<br/>";
        content += "即可正常打开文件。";

        ExcelService excelService = new ExcelService();
        excelService.addExcel(new WeekRecord());

        mailService.sendAttachmentsMail("fyf4831@dingtalk.com", "主题：马来酸的工作记录 2017-12-4", content,excelService.doMake());

        model.put("time", new Date());
        model.put("message", "send mail success!");
        ModelAndView modelAndView = new ModelAndView("user/sendMail");
        modelAndView.addObject("time", new Date());
        modelAndView.addObject("message", "send mail success!");
        return modelAndView;
    }
}
