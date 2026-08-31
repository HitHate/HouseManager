package com.controller;


import com.entity.User;
import com.entity.Userlist;
import com.service.UserService;
import com.utils.Result;
import com.vo.UserInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;


@RequestMapping(value = "/api/*", method = RequestMethod.POST)
@RestController
@CrossOrigin
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("user/register")
    public Result register(@RequestBody User user){

        return userService.addUser(user);
    }

    @RequestMapping("user/login")
    public Result login(@RequestBody User user){

        return userService.login(user);
    }

    @RequestMapping("user/slogin")
    public Result slogin(@RequestBody User user){

        return userService.login(user);
    }

    @RequestMapping(value = "user/getUserList" , method = RequestMethod.GET)
    public Result userList(HttpServletRequest request,String userid){
        System.out.println(userid);
        System.out.println(userService.userList(request).toString());
        return userService.userList(request);
    }

    @RequestMapping(value = "getUserList" , method = RequestMethod.GET)
    public Result AdminList(HttpServletRequest request,String userid){
        return userService.userList(request);
    }

    @RequestMapping("editUser")
    public Result editUser(@RequestBody Userlist user,HttpServletRequest request){

        return userService.editUser(user,request);
    }

    @RequestMapping("editUserfd")
    public Result editUserfd(@RequestBody Userlist user,HttpServletRequest request){

        return userService.editUser(user,request);
    }

    @RequestMapping(value = "editUserPass",method = RequestMethod.POST)
    public Result editPass(@RequestBody HashMap<String,Object> map, HttpServletRequest request){
        return userService.editPass(map,request);
    }

    @RequestMapping(value = "editUserPassfd",method = RequestMethod.POST)
    public Result editPassfd(@RequestBody HashMap<String,Object> map, HttpServletRequest request){
        return userService.editPass(map,request);
    }
}
