package com.mmall.service;

import com.mmall.common.ServerResponse;

/**
 * @author alone95
 * @date 2018/5/18
 */
public interface IRecommendService {

    ServerResponse productRecommend(Integer userId,Integer categoryId,Integer num);
}
