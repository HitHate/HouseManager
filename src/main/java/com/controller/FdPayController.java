package com.controller;

import com.entity.Topaid;
import com.service.FdPayService;
import com.utils.Result;
import com.vo.SearchBean;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

@RestController
@CrossOrigin
@RequestMapping("api/*")
public class FdPayController {


    @Resource
    private FdPayService fdPayService;

    @RequestMapping(value = "topaidlistfd",method = RequestMethod.GET)
    public HashMap<String,Object> topaidlistfd(SearchBean searchBean){
        return fdPayService.topaidlistfd(searchBean);
    }

    @RequestMapping(value = "addtopaid",method = RequestMethod.POST)
    public Result addtopaid(@RequestBody Topaid topaid){
        return fdPayService.addtopaid(topaid);
    }
}
