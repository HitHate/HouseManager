package com.service.impl;

import com.entity.Paid;
import com.entity.Solve;
import com.entity.Wrong;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.SolveMapper;
import com.mapper.WrongMapper;
import com.service.WrongService;
import com.utils.Result;
import com.vo.SearchBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class WrongServiceImpl implements WrongService {

    @Resource
    private WrongMapper wrongMapper;

    @Resource
    private SolveMapper solveMapper;

    @Override
    public Result addWrong(Wrong wrong) {
        wrong.setStatus("待处理");
        wrongMapper.insert(wrong);
        return new Result(200,"报修成功，待处理");
    }

    @Override
    public Result findWrong(String name) {

        List<Wrong> wrongs = wrongMapper.findByName(name);

        return new Result(200,wrongs,"查询成功");
    }

    @Override
    public Result findSolveWrong(String name) {

        List<Solve> solves = solveMapper.findByName(name);

        return new Result(200,solves,"查询成功");
    }

    @Override
    public HashMap<String,Object> findSolveWrongfd(SearchBean searchBean) {
        Integer cur = searchBean.getCur();
        Integer size = searchBean.getSize();

        PageHelper.startPage(cur,size);//此代码必须写在查询代码上方

        List<Solve> solves = wrongMapper.findAll(searchBean);

        PageInfo<Solve> pageInfo = new PageInfo<>(solves);
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("list",pageInfo.getList());
        map.put("total",pageInfo.getTotal());
        return map;
    }

    @Override
    public Result handleWrong(Integer id) {
        Wrong wrong = wrongMapper.selectByPrimaryKey(id);
        wrong.setStatus("已处理");
        solveMapper.insertByWrong(wrong);
        wrongMapper.deleteByPrimaryKey(id);
        return new Result(200,"处理完毕");
    }
}
