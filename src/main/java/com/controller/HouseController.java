package com.controller;


import com.entity.Houselist;
import com.service.HouseService;
import com.utils.Result;
import com.vo.SearchBean;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/api/*")
@CrossOrigin
public class HouseController {

    @Resource
    private HouseService houseService;

    @RequestMapping(value = "houselist",method = RequestMethod.POST)
    public HashMap<String,Object> findAll(SearchBean searchBean){
        return houseService.findAll(searchBean);
    }

    @RequestMapping(value = "addhouse",method = RequestMethod.POST)
    public Result addhouse(@RequestBody Houselist house){
        return houseService.addhouse(house);
    }

    @RequestMapping(value = "edithouse",method = RequestMethod.POST)
    public int edithouse(@RequestBody Houselist house){
        return houseService.edithouse(house);
    }

    @RequestMapping(value = "deletehouse",method = RequestMethod.POST)
    public Result deletehouse(@RequestBody List<String> list){
        return houseService.deletehouse(list);
    }
}
