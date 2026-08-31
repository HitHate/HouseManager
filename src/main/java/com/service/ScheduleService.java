package com.service;

import com.entity.Schedule;
import com.utils.Result;
import com.vo.SearchBean;

import java.util.HashMap;
import java.util.List;

public interface ScheduleService {
    HashMap<String, Object> findAll(SearchBean searchBean);

    Result insertschedule(Schedule schedule);

    Result updateSchedule(Schedule schedule);

    Result delSchedule(List<String> list);
}
