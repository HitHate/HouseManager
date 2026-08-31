package com.service.impl;

import com.entity.Houselist;
import com.entity.Topaid;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.TopaidMapper;
import com.service.FdPayService;
import com.utils.Result;
import com.vo.SearchBean;
import com.vo.ZulistBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class FdPayServiceImpl implements FdPayService {

    @Resource
    private TopaidMapper topaidMapper;

    @Override
    public Result addtopaid(Topaid topaid) {

        topaidMapper.updateByHouseId(topaid);
        return new Result(200,"截止日期已更新");
    }

    @Override
    public HashMap<String,Object> topaidlistfd(SearchBean searchBean) {
        Integer cur = searchBean.getCur();
        Integer size = searchBean.getSize();


        PageHelper.startPage(cur,size);//此代码必须写在查询代码上方

        List<ZulistBean> zulistBeans = topaidMapper.findAll(searchBean);


        PageInfo<ZulistBean> pageInfo = new PageInfo<>(zulistBeans);
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("list",pageInfo.getList());
        map.put("total",pageInfo.getTotal());
        return map;
    }
}
