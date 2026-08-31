package com.controller;


import com.service.ZuListService;
import com.utils.Result;
import com.vo.SearchBean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

@RestController
@RequestMapping("api/*")
@CrossOrigin
public class ZuListController {


    @Resource
    private ZuListService zuListService;


    @RequestMapping(value = "zulist",method = RequestMethod.GET)
    public HashMap<String,Object> findAll(SearchBean searchBean){

        return zuListService.findAll(searchBean);
    }
}
