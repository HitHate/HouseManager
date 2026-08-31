package com.service.impl;

import com.entity.Houselist;
import com.entity.Paid;
import com.entity.Topaid;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.PaidMapper;
import com.mapper.TopaidMapper;
import com.service.PayService;
import com.utils.Result;
import com.vo.SearchBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class PayServiceImpl implements PayService {

    @Resource
    private TopaidMapper topaidMapper;

    @Resource
    private PaidMapper paidMapper;

    @Override
    public Result findByName(String name) {

        List<Topaid> topaidList = topaidMapper.findByName(name);

        return new Result(200,topaidList,"查询成功");
    }

    @Override
    public Result goToPay(String id) {


        Topaid topaid = topaidMapper.findByHouseId(id);

        topaid.setStatus("已付清");

        Paid paid = new Paid();

        paid.setPaydate(new Date());

        paid.setHouse_id(id);

        paidMapper.insertByTopaid(topaid);

        paidMapper.updateByHouseId(paid);

        topaidMapper.deleteByHouseId(id);


        return new Result(200,"支付成功");
    }

    @Override
    public HashMap<String,Object> selectAll(SearchBean searchBean) {
        Integer cur = searchBean.getCur();
        Integer size = searchBean.getSize();

        PageHelper.startPage(cur,size);//此代码必须写在查询代码上方

        List<Paid> paids = paidMapper.findAll(searchBean);

        PageInfo<Paid> pageInfo = new PageInfo<>(paids);
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("list",pageInfo.getList());
        map.put("total",pageInfo.getTotal());
        return map;
    }

    @Override
    public Result delpaid(List<String> list) {
        for (String s : list) {
            paidMapper.deleteByHouseId(s);
        }
        return new Result(200,"删除成功");
    }

}
