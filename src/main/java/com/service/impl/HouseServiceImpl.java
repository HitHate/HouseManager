package com.service.impl;


import com.entity.Houselist;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.HouselistMapper;
import com.service.HouseService;
import com.utils.Result;
import com.vo.SearchBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class HouseServiceImpl implements HouseService {

    @Resource
    private HouselistMapper houselistMapper;

    public HashMap<String,Object> findAll(SearchBean searchBean) {

        Integer cur = searchBean.getCur();
        Integer size = searchBean.getSize();


        PageHelper.startPage(cur,size);//此代码必须写在查询代码上方

        List<Houselist> houselists = houselistMapper.findAll(searchBean);


        PageInfo<Houselist> pageInfo = new PageInfo<>(houselists);
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("list",pageInfo.getList());
        map.put("total",pageInfo.getTotal());
        return map;
    }

    @Override
    public Result addhouse(Houselist house) {

        if(houselistMapper.findByHouseId(house) != null){
            return new Result(100,"房屋ID已存在");
        }

        if(houselistMapper.findByAddress(house) == null){
            houselistMapper.insert(house);
            return new Result(200,"添加成功^v^");
        }
        return new Result(50,"房屋已存在");
    }

    @Override
    public int edithouse(Houselist house) {

        if(houselistMapper.findByAddress(house) == null){
            houselistMapper.updateByPrimaryKey(house);
            return 200;
        }
        return 100;
    }

    @Override
    public Result deletehouse(List<String> list) {
        for (String s : list) {
            houselistMapper.deleteByHouseId(s);
        }
        return new Result(200,"删除成功");
    }
}
