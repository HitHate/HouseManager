package com.service;

import com.utils.Result;
import com.vo.SearchBean;
import com.vo.ZulistBean;

import java.util.HashMap;
import java.util.List;

public interface ZuListService {

    HashMap<String,Object> findAll(SearchBean searchBean);

}
