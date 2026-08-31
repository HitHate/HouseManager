package com.service;

import com.utils.Result;
import com.vo.SearchBean;

import java.util.HashMap;
import java.util.List;

public interface PayService {
    Result findByName(String name);

    Result goToPay(String id);

    HashMap<String,Object> selectAll(SearchBean searchBean);

    Result delpaid(List<String> list);
}
