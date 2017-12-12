package com.maleic.demo.controller;

import com.maleic.demo.domain.Record;
import com.maleic.demo.domain.RecordRepository;
import com.maleic.demo.service.ExcelService;
import com.maleic.demo.utils.excel.WeekRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.sun.tools.doclets.formats.html.markup.HtmlStyle.title;

@Controller
@RequestMapping("/kalendar")
public class KalendarController {

    @Autowired
    private RecordRepository recordRepository;

    @GetMapping("/index")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("kalendar/index");
        modelAndView.addObject("time", new Date());
        modelAndView.addObject("message", "send mail success!");
        return modelAndView;
    }

    @ResponseBody
    @GetMapping("/getEvents")
    public HashMap getEvents(Long from){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        List<Integer> list = new ArrayList<Integer>();
        Calendar cal = Calendar.getInstance();

        Date currentDate;
        if (from == null){
            currentDate = new Date();
        }else {
            currentDate = new Date(from);
        }

        cal.setTime(currentDate);
        cal.set(Calendar.DATE, 1);
        int month = cal.get(Calendar.MONTH);

        while(cal.get(Calendar.MONTH) == month){
            list.add(new Integer(sdf.format(cal.getTime())));
            cal.add(Calendar.DATE, 1);
        }

        List<Record> records =  recordRepository.findByDidIn(list);

        HashMap ret = new HashMap();
        List result = new ArrayList();
        ret.put("success",1);

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        try {
        for (Record record :records) {
            HashMap data = new HashMap();
            data.put("id",record.getRid());

            String startTime = record.getStartTime();
            String startTimeStr = record.getDid().toString() + " " + startTime;
            Date startTimeDate;
            startTimeDate = format.parse(startTimeStr);
            double startTimeMillis = startTimeDate.getTime();
            startTime = startTime.substring(0,startTime.length()-3);

            String endTime = record.getEndTime();
            String endTimeStr = record.getDid().toString() + " " + endTime;
            Date endTimeDate;
            endTimeDate = format.parse(endTimeStr);
            double endTimeMillis = endTimeDate.getTime();
            endTime = endTime.substring(0,endTime.length()-3);

            double hour = (endTimeMillis - startTimeMillis) / 1000 / 60 / 60;

            String title = record.getExplain();
            data.put("title", title + " " + startTime + "~" + endTime);
            data.put("class","event-information");
            data.put("start",startTimeMillis);
            data.put("end",endTimeMillis);
            data.put("hour",new DecimalFormat("#.00").format(hour));
            result.add(data);
        }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ret.put("result",result);

//        "success": 1,
//        "result": [
//        {
//            "id": 293,
//                "title": "Event 1",
//                "url": "http://example.com",
//                "class": "event-important",
//                "start": 12039485678000, // Milliseconds
//                "end": 1234576967000 // Milliseconds
//        },
//		...
//	]
        return ret;
    }
}
