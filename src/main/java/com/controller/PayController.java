package com.controller;


import com.service.PayService;
import com.utils.Result;
import com.vo.SearchBean;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/*")

public class PayController {


    @Resource
    private PayService payService;

    @RequestMapping(value = "topaidlistU",method = RequestMethod.GET)
    Result topay(String nickname){
        return payService.findByName(nickname);
    }

    @RequestMapping(value = "gotopay",method = RequestMethod.GET)
    Result gotopay(String house_id){
        return payService.goToPay(house_id);
    }

    @RequestMapping(value = "selectall",method = RequestMethod.POST)
    HashMap<String,Object> selectAll(@RequestBody SearchBean searchBean){
        return payService.selectAll(searchBean);
    }

    @RequestMapping(value = "delpaid",method = RequestMethod.POST)
    Result delpaid(@RequestBody List<String> list){
        return payService.delpaid(list);
    }


}
