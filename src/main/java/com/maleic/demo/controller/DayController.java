package com.maleic.demo.controller;

import com.maleic.demo.domain.Day;
import com.maleic.demo.domain.DayRepository;
import com.maleic.demo.domain.Record;
import com.maleic.demo.domain.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/day")
public class DayController {

    @Autowired
    private DayRepository dayRepository;

    @Autowired
    private RecordRepository recordRepository;

    /**
     * 获取所有日期记录
     * @return
     */
    @GetMapping(value = "")
    public List<Day> dayList() {
        return dayRepository.findAll();
    }

    @GetMapping(value = "/records")
    public List<Record> recordList() {
        return recordRepository.findAll();
    }

    @GetMapping(value = "/records1")
    public List<Record> recordList1() {
        return recordRepository.findByDid(2017120);
    }

    /**
     * 添加日期记录
     * @param did
     * @param desc
     * @return
     */
    @PostMapping(value = "/days")
    public Day dayAdd(@RequestParam("did") Integer did, @RequestParam("desc") String desc) {

        Day day = new Day();
        day.setDid(did);
        day.setDesc(desc);
        return dayRepository.save(day);
    }

    @GetMapping(value = "/index")
    public String index() {
        return "index";
    }
}
