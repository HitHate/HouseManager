package com.service;

import com.entity.Topaid;
import com.utils.Result;
import com.vo.SearchBean;

import java.util.HashMap;

public interface FdPayService {
    Result addtopaid(Topaid topaid);

    HashMap<String,Object> topaidlistfd(SearchBean searchBean);
}
