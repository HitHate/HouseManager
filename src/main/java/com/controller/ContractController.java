package com.controller;


import com.entity.Apply;
import com.entity.Hetong;
import com.service.ContractService;
import com.utils.Result;
import com.vo.ApplyBean;
import com.vo.SearchBean;
import com.vo.ZulistBean;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/*")
@CrossOrigin
public class ContractController {


    @Resource
    private ContractService contractService;

    @RequestMapping(value = "applycheckuserlist",method = RequestMethod.POST)
    public Result PostContract(@RequestBody Hetong hetong, HttpServletRequest request){

        return contractService.UserPostContract(hetong,request);

    }

    @RequestMapping(value = "applylist",method = RequestMethod.GET)
    public HashMap<String,Object> WaitContract(SearchBean searchBean,HttpServletRequest request){
        return contractService.WaitContract(searchBean,request);

    }

    @RequestMapping(value = "applylistfd",method = RequestMethod.GET)
    public HashMap<String,Object> WaitContractfd(SearchBean searchBean){
        return contractService.WaitContractfd(searchBean);

    }

    @RequestMapping(value = "findHetong",method = RequestMethod.GET)
    public Result FindContract(Integer contract_id){
        return contractService.FindContract(contract_id);

    }

    @RequestMapping(value = "addApplyout",method = RequestMethod.GET)
    public Result CancelContract(Integer contract_id){
        return contractService.CancelContract(contract_id);
    }


    @RequestMapping(value = "addhetong",method = RequestMethod.POST)
    public Result addHetong(@RequestBody Hetong hetong){
        return contractService.addHetong(hetong);
    }


    @RequestMapping(value = "applyout",method = RequestMethod.GET)
    public HashMap<String,Object> applyOutList(SearchBean searchBean){
        return contractService.applyOut(searchBean);
    }

    @RequestMapping(value = "checkout",method = RequestMethod.GET)
    public HashMap<String,Object> CheckOut(SearchBean searchBean){
        return contractService.CheckOut(searchBean);
    }

    @RequestMapping(value = "deleteCheck",method = RequestMethod.POST)
    public Result CheckOut(@RequestBody List<String> list){
        return contractService.deleteCheck(list);
    }

    @RequestMapping(value = "deletehetong",method = RequestMethod.GET)
    public Result CheckOut(Integer contract_id){
        return contractService.deletehetong(contract_id);
    }

    @RequestMapping(value = "agreeapplyout",method = RequestMethod.GET)
    public Result agreeapplyout(String house_id){
        return contractService.agreeapplyout(house_id);
    }

    @RequestMapping(value = "jujueApplyout",method = RequestMethod.GET)
    public Result jujueApplyout(String house_id){
        return contractService.jujueApplyout(house_id);
    }

    @RequestMapping(value = "edithetong",method = RequestMethod.POST)
    public Result edithetong(@RequestBody Hetong hetong){
        return contractService.edithetong(hetong);
    }


    @RequestMapping(value = "Noapply",method = RequestMethod.GET)
    public Result Noapply(String house_id){
        return contractService.Noapply(house_id);
    }
}

