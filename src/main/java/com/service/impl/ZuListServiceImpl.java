package com.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.ZulistMapper;
import com.service.ZuListService;
import com.utils.Result;
import com.vo.ApplyBean;
import com.vo.SearchBean;
import com.vo.ZulistBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class ZuListServiceImpl implements ZuListService {


    @Resource
    private ZulistMapper zulistMapper;

    @Override
    public HashMap<String,Object> findAll(SearchBean searchBean) {

        PageHelper.startPage(searchBean.getCur(),searchBean.getSize());

        List<ZulistBean> zulistBeans = zulistMapper.findAll(searchBean);

        PageInfo<ZulistBean> pageInfo = new PageInfo<>(zulistBeans);

        HashMap<String,Object> map = new HashMap<>();

        map.put("list",pageInfo.getList());

        map.put("total",pageInfo.getTotal());

        return map;
    }

}
