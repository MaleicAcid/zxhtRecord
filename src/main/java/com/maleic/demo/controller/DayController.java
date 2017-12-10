package com.maleic.demo.controller;

import com.maleic.demo.domain.Day;
import com.maleic.demo.domain.DayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/day")
public class DayController {

    @Autowired
    private DayRepository dayRepository;

    /**
     * 获取所有日期记录
     * @return
     */
    @GetMapping(value = "/days")
    public List<Day> dayList() {
        return dayRepository.findAll();
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
