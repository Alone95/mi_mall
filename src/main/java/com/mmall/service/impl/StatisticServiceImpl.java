package com.mmall.service.impl;

import com.mmall.common.ServerResponse;
import com.mmall.dao.StatisticMapper;
import com.mmall.service.IStatisticService;
import com.mmall.vo.StatisticVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author alone95
 * @date 2018/5/5
 */
@Service
public class StatisticServiceImpl implements IStatisticService {

    @Autowired
    private StatisticMapper statisticMapper;

    @Override
    public ServerResponse<StatisticVo> getBaseCount() {
        return ServerResponse.createBySuccess(statisticMapper.getBaseCount());
    }
}
