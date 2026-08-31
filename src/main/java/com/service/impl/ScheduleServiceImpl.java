package com.service.impl;


import com.entity.Houselist;
import com.entity.Schedule;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.ScheduleMapper;
import com.service.ScheduleService;
import com.utils.Result;
import com.vo.SearchBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService {

    @Resource
    private ScheduleMapper scheduleMapper;

    @Override
    public HashMap<String, Object> findAll(SearchBean searchBean) {
        Integer cur = searchBean.getCur();
        Integer size = searchBean.getSize();


        PageHelper.startPage(cur,size);//此代码必须写在查询代码上方

        List<Schedule> schedules = scheduleMapper.findAll(searchBean);

        PageInfo<Schedule> pageInfo = new PageInfo<>(schedules);
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("list",pageInfo.getList());
        map.put("total",pageInfo.getTotal());
        return map;
    }

    @Override
    public Result insertschedule(Schedule schedule) {

        Schedule schedule1 = scheduleMapper.findByDate(schedule);
        if (schedule1 == null){
            scheduleMapper.insert(schedule);
            return new Result(200,"添加日程成功");
        }else {
            String content = schedule1.getContent() +"; " + schedule.getContent();
            schedule.setContent(content);
            scheduleMapper.updateByDate(schedule);
            return new Result(200,"更新日程成功");
        }
    }

    @Override
    public Result updateSchedule(Schedule schedule) {

        scheduleMapper.updateByDate(schedule);

        return new Result(200,"更新日程成功");
    }

    @Override
    public Result delSchedule(List<String> list) {
        for (String s : list) {
            scheduleMapper.deleteByDate(s);
        }
        return new Result(200,"删除成功");
    }
}
