package com.mmall.service;

import com.mmall.common.ServerResponse;

import java.util.Map;

/**
 * @author alone95
 * @date 2017/12/5
 */
public interface IOrderService {

    ServerResponse pay(Long orderNo, Integer userId, String path);

    public ServerResponse aliCallback(Map<String,String> params);

    public ServerResponse queryOrderPayStatus(Integer userId,Long orderNo);
}
