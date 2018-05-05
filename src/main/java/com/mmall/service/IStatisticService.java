package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.vo.StatisticVo;

/**
 * @author alone95
 * @date 2018/5/5
 */
public interface IStatisticService {

    ServerResponse<StatisticVo> getBaseCount();
}
