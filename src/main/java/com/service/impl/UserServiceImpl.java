package com.service.impl;

import com.config.JwtUtils;
import com.entity.User;
import com.entity.Userlist;
import com.mapper.UserMapper;
import com.mapper.UserlistMapper;
import com.service.UserService;
import com.utils.Result;

import com.vo.UserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private JwtUtils jwtUtils;

    @Resource
    private UserlistMapper userlistMapper;


    @Override
    public Result addUser(User user) {

        if(userMapper.findByName(user.getUsername()) == null){
            user.setType("用户");
            userMapper.insertSelective(user);
            return new Result(200,"注册成功");
        }
        return new Result(100,"用户已被注册");
    }

    @Override
    public Result login(User user) {

        UserInfo u = userMapper.findUser(user);

        if(u == null){

            return new Result(100,"用户名或密码错误或身份错误");

        }

        String uuid = jwtUtils.createToken(u.getId()+"");

        return new Result(200,u,"登陆成功",uuid);
    }



    @Override
    public Result userList(HttpServletRequest request) {

        String token = request.getHeader("token");

        String uuid = jwtUtils.getUsernameFromToken(token);


        Integer id = Integer.parseInt(uuid);

        System.out.println(id);

        Userlist userlist = userlistMapper.findByUid(id);

        return new Result(200,userlist,"查询成功");

    }

    @Override
    public Result editUser(Userlist user,HttpServletRequest request) {

        String token = request.getHeader("token");

        System.out.println(token.length());

        String uuid = jwtUtils.getUsernameFromToken(token);

        Integer id = Integer.parseInt(uuid);

        User u = userMapper.selectByPrimaryKey(id);

        user.setUser_id(id);

        u.setNickname(user.getNickname());

        user.setName(u.getUsername());

        if(userlistMapper.findByUid(id) == null){

            userlistMapper.insertSelective(user);

            userMapper.updateByPrimaryKeySelective(u);

            return new Result(200,"添加成功");
        }else {

            userMapper.updateByPrimaryKeySelective(u);

            userlistMapper.updateByUid(user);

            return new Result(200,"修改成功");
        }

    }

    @Override
    public Result editPass(HashMap<String, Object> map, HttpServletRequest request) {

        String token = request.getHeader("token");
        System.out.println(token.length());
        String uuid = jwtUtils.getUsernameFromToken(token);
        Integer id = Integer.parseInt(uuid);

        String password = (String) map.get("pass");

        User user = userMapper.selectByPrimaryKey(id);

        if (password.equals(user.getPassword())){

            return new Result(100,"新密码不可与旧密码一致");
        }else {
            user.setPassword(password);
            userMapper.updateByPrimaryKeySelective(user);
            return new Result(200,"修改成功");
        }
    }


}
