package com.controller;


import com.entity.Schedule;
import com.service.ScheduleService;
import com.utils.Result;
import com.vo.SearchBean;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/*")
public class ScheduleController {


    @Resource
    private ScheduleService service;

    @RequestMapping(value = "schedulelist",method = RequestMethod.GET)
    public HashMap<String,Object> ScheduleList(SearchBean searchBean){
        return service.findAll(searchBean);
    }

    @RequestMapping(value = "insertschedule",method = RequestMethod.POST)
    public Result insertSchedule(@RequestBody Schedule schedule){
        return service.insertschedule(schedule);
    }

    @RequestMapping(value = "editschedule",method = RequestMethod.POST)
    public Result updateSchedule(@RequestBody Schedule schedule){
        return service.updateSchedule(schedule);
    }

    @RequestMapping(value = "delSchedule",method = RequestMethod.POST)
    public Result delSchedule(@RequestBody List<String> list){
        return service.delSchedule(list);
    }
}
