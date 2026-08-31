package com.controller;

import com.entity.Wrong;
import com.service.WrongService;
import com.utils.Result;
import com.vo.SearchBean;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

@RestController
@CrossOrigin
@RequestMapping("api/*")

public class WrongController {


    @Resource
    private WrongService wrongService;

    @RequestMapping(value = "addwrong",method = RequestMethod.POST)
    public Result addWrong(@RequestBody Wrong wrong){
        return wrongService.addWrong(wrong);
    }

    @RequestMapping(value = "wronglist",method = RequestMethod.GET)
    public Result findWrong(String name){
        return wrongService.findWrong(name);
    }

    @RequestMapping(value = "solvelistU",method = RequestMethod.GET)
    public Result findSolveWrong(String name){
        return wrongService.findSolveWrong(name);
    }

    @RequestMapping(value = "solvelist",method = RequestMethod.POST)
    public HashMap<String,Object> findSolveWrongfd(@RequestBody SearchBean searchBean){
        return wrongService.findSolveWrongfd(searchBean);
    }

    @RequestMapping(value = "handleWrong",method = RequestMethod.GET)
    public Result handleWrong(Integer id){
        return wrongService.handleWrong(id);
    }

}
