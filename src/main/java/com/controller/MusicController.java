package com.controller;

import com.service.MusicService;
import com.utils.Result;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("api/*")
@RestController
@CrossOrigin
public class MusicController {
    @Resource
    private MusicService musicService;
    //1.获取音乐
    @RequestMapping("music")
    public Object music(){
        Result result = musicService.findAll();
        return result;
    }
}
