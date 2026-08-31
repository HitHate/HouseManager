package com.service;

import com.entity.Wrong;
import com.utils.Result;
import com.vo.SearchBean;

import java.util.HashMap;

public interface WrongService {

     Result addWrong(Wrong wrong);

     Result findWrong(String name);

     Result findSolveWrong(String name);

     HashMap<String,Object> findSolveWrongfd(SearchBean searchBean);

    Result handleWrong(Integer id);
}
