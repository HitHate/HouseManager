package com.service;

import com.entity.User;
import com.entity.Userlist;
import com.utils.Result;


import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public interface UserService {
    Result addUser(User user);

    Result login(User user);

    Result userList(HttpServletRequest request);

    Result editUser(Userlist user,HttpServletRequest request);

    Result editPass(HashMap<String, Object> map, HttpServletRequest request);

}
