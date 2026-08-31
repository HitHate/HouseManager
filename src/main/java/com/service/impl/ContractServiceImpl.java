package com.service.impl;

import com.config.JwtUtils;
import com.entity.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.*;
import com.service.ContractService;
import com.utils.Result;
import com.vo.ApplyBean;
import com.vo.SearchBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class ContractServiceImpl implements ContractService {

    @Resource
    private HouselistMapper houselistMapper;

    @Resource
    private ApplyMapper applyMapper;

    @Resource
    private JwtUtils jwtUtils;

    @Resource
    private HetongMapper hetongMapper;

    @Resource
    private ZulistMapper zulistMapper;

    @Resource
    private ApplyoutMapper applyoutMapper;

    @Resource
    private CheckoutMapper checkoutMapper;

    @Resource
    private TopaidMapper topaidMapper;

    @Override
    public Result UserPostContract(Hetong hetong, HttpServletRequest request) {

        String token = request.getHeader("token");

        String uuid = jwtUtils.getUsernameFromToken(token);

        int id = Integer.parseInt(uuid);


        Apply apply = new Apply();

        apply.setStatus("申请中");

        apply.setHouse_id(hetong.getHouse_id());
        apply.setAddress(hetong.getAddress());
        apply.setArea(hetong.getArea());
        apply.setPrice(hetong.getPrice());
        apply.setUser_id(id);

        applyMapper.insertSelective(apply);

        houselistMapper.updateStatus(apply);

        hetong.setUser_id(id);

        hetongMapper.insertSelective(hetong);

        return new Result(200,"合同发送成功，请耐心等待生效");

    }

    @Override
    public HashMap<String, Object> WaitContract(SearchBean searchBean,HttpServletRequest request) {

        String token = request.getHeader("token");

        String uuid = jwtUtils.getUsernameFromToken(token);

        Integer id = Integer.parseInt(uuid);

        Integer cur = searchBean.getCur();

        Integer size = searchBean.getSize();

        PageHelper.startPage(cur,size);//此代码必须写在查询代码上方

        List<ApplyBean> applyBeans = applyMapper.findByUid(searchBean);

        PageInfo<ApplyBean> pageInfo = new PageInfo<>(applyBeans);

        HashMap<String,Object> map = new HashMap<String,Object>();

        map.put("list",pageInfo.getList());

        map.put("total",pageInfo.getTotal());

        return map;
    }

    @Override
    public Result FindContract(Integer id) {

        Hetong hetong = hetongMapper.findByContractId(id);

        return new Result(200,hetong,"查询成功");
    }

    @Override
    public Result CancelContract(Integer contract_id) {


        HashMap<String, Object> map = new HashMap<>();

        map.put("contract_id",contract_id);
        map.put("status","退租中");

        zulistMapper.updateStatus(map);

        Zulist zulist = zulistMapper.findByContractId(contract_id);
        applyoutMapper.insertByZulist(zulist);

        return new Result(200,"申请成功");
    }

    @Override
    public HashMap<String, Object> WaitContractfd(SearchBean searchBean) {

        Integer cur = searchBean.getCur();

        Integer size = searchBean.getSize();

        PageHelper.startPage(cur,size);//此代码必须写在查询代码上方

        List<ApplyBean> applyBeans = applyMapper.findAll(searchBean);

        PageInfo<ApplyBean> pageInfo = new PageInfo<>(applyBeans);

        HashMap<String,Object> map = new HashMap<String,Object>();

        map.put("list",pageInfo.getList());

        map.put("total",pageInfo.getTotal());

        return map;
    }

    @Override
    public Result addHetong(Hetong hetong) {
        String houseid = hetong.getHouse_id();

        Hetong hetong1 = hetongMapper.findByHouseId(houseid);

        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");

        Topaid topaid = new Topaid();

        topaid.setAddress(hetong1.getAddress());
        topaid.setHouse_id(houseid);
        topaid.setPrice(hetong1.getPrice());
        try {
            topaid.setDate(ft.parse(hetong1.getTodate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        topaid.setName(hetong1.getZuke());
        topaid.setUser_id(hetong1.getUser_id());
        topaid.setStatus("待支付");
        topaidMapper.insert(topaid);


        int id = hetong1.getId();

        hetong1.setChuzu(hetong.getChuzu());
        hetong1.setChuzu_idcard(hetong.getChuzu_idcard());
        hetong1.setPayday(hetong.getPayday());

        hetongMapper.updateByPrimaryKey(hetong1);

        zulistMapper.insertByHetong(hetong1);

        HashMap<String, Object> map = new HashMap<>();

        map.put("contract_id",id);
        map.put("status","租赁中");

        zulistMapper.updateStatus(map);

        Apply apply = new Apply();
        apply.setHouse_id(houseid);
        apply.setStatus("已租赁");
        houselistMapper.updateStatus(apply);

        applyMapper.deleteByHouseId(houseid);

        return new Result(200,"合同签属成功");
    }

    @Override
    public HashMap<String,Object> applyOut(SearchBean searchBean) {

        PageHelper.startPage(searchBean.getCur(),searchBean.getSize());
        List<ApplyBean> applyBeans= applyoutMapper.findApplyOut(searchBean);

        PageInfo<ApplyBean> pageInfo = new PageInfo<>(applyBeans);

        HashMap<String,Object> map = new HashMap<>();

        map.put("list",pageInfo.getList());

        map.put("total",pageInfo.getTotal());

        return map;
    }

    @Override
    public HashMap<String, Object> CheckOut(SearchBean searchBean) {

        PageHelper.startPage(searchBean.getCur(),searchBean.getSize());
        List<Checkout> checkOut= checkoutMapper.findCheckOut(searchBean);

        PageInfo<Checkout> pageInfo = new PageInfo<>(checkOut);

        HashMap<String,Object> map = new HashMap<>();

        map.put("list",pageInfo.getList());

        map.put("total",pageInfo.getTotal());

        return map;
    }

    @Override
    public Result deleteCheck(List<String> list) {
        for (String s : list) {
            checkoutMapper.deleteByHouseId(s);
        }
        return new Result(200,"删除成功");
    }

    @Override
    public Result deletehetong(Integer contract_id) {

        Zulist zulist = zulistMapper.findByContractId(contract_id);
        zulist.setStatus("已被房东终止合同");
        checkoutMapper.insertByZulist(zulist);

        Apply apply = new Apply();
        apply.setHouse_id(zulist.getHouse_id());
        apply.setStatus("未租赁");
        houselistMapper.updateStatus(apply);
        hetongMapper.deleteByPrimaryKey(contract_id);
        zulistMapper.deleteByCid(contract_id);

        return new Result(200,"已终止合同");
    }

    @Override
    public Result Noapply(String house_id) {


        Apply apply1 = applyMapper.findByHouseId(house_id);

        applyMapper.deleteByHouseId(house_id);

        Apply apply = new Apply();
        apply.setHouse_id(house_id);
        apply.setStatus("未租赁");
        houselistMapper.updateStatus(apply);

        hetongMapper.deleteByHouseId(house_id);

        apply1.setStatus("房东拒绝租赁");
        checkoutMapper.insertByApply(apply1);

        return new Result(200,"已拒绝租赁");
    }

    @Override
    public Result agreeapplyout(String house_id) {
        Applyout applyout = applyoutMapper.findByHouseId(house_id);
        applyout.setStatus("已退租");

        checkoutMapper.insertByApplyout(applyout);

        zulistMapper.deleteByHouseId(house_id);
        hetongMapper.deleteByHouseId(house_id);

        Apply apply1 = new Apply();
        apply1.setHouse_id(house_id);
        apply1.setStatus("未租赁");
        houselistMapper.updateStatus(apply1);

        applyoutMapper.deleteByHouseId(house_id);

        return new Result(200,"已同意退租");
    }

    @Override
    public Result jujueApplyout(String house_id) {

        HashMap<String, Object> map = new HashMap<>();

        Hetong hetong = hetongMapper.findByHouseId(house_id);

        map.put("contract_id",hetong.getId());
        map.put("status","租赁中");


        zulistMapper.updateStatus(map);

        applyoutMapper.deleteByHouseId(house_id);

        return new Result(200,"已拒绝退租");
    }

    @Override
    public Result edithetong(Hetong hetong) {
        hetongMapper.updateByHouseId(hetong);
        return new Result(200,"更新合同成功");
    }
}
