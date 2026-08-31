package com.service.impl;

import com.entity.Music;
import com.mapper.MusicMapper;
import com.service.MusicService;
import com.utils.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service
public class MusicServiceImpl implements MusicService {
    @Resource
    private MusicMapper musicMapper;

    @Override
    public Result findAll() {
        List<Music> music = musicMapper.findAll();
        return new Result(200,music,"查询成功");
    }
}
