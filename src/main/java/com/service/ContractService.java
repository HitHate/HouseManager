package com.service;

import com.entity.Apply;

import com.entity.Hetong;
import com.utils.Result;
import com.vo.SearchBean;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

public interface ContractService {

    Result UserPostContract(Hetong hetong, HttpServletRequest request);

    HashMap<String,Object> WaitContract(SearchBean searchBean,HttpServletRequest request);

    Result FindContract(Integer id);

    Result CancelContract(Integer contract_id);

    HashMap<String, Object> WaitContractfd(SearchBean searchBean);

    Result addHetong(Hetong hetong);

    HashMap<String,Object> applyOut(SearchBean searchBean);

    HashMap<String, Object> CheckOut(SearchBean searchBean);

    Result deleteCheck(List<String> list);

    Result deletehetong(Integer contract_id);

    Result Noapply(String house_id);

    Result agreeapplyout(String house_id);

    Result jujueApplyout(String house_id);

    Result edithetong(Hetong hetong);
}
