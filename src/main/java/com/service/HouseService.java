package com.service;

import com.entity.Houselist;
import com.utils.Result;
import com.vo.SearchBean;

import java.util.HashMap;
import java.util.List;

public interface HouseService {

    HashMap<String,Object> findAll(SearchBean searchBean);

    Result addhouse(Houselist house);

    int edithouse(Houselist house);

    Result deletehouse(List<String> list);
}
