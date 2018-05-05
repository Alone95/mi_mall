package com.mmall.controller.backend;

import com.mmall.common.ServerResponse;
import com.mmall.service.IStatisticService;
import com.mmall.vo.StatisticVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author alone95
 * @date 2018/5/5
 */
@Controller
@RequestMapping("/manage/statistic/")
public class StatisticController {

    @Autowired

    private IStatisticService statisticService;

    @RequestMapping("base_count.do")
    @ResponseBody
    public ServerResponse<StatisticVo> getStatistics() {
        return statisticService.getBaseCount();
    }
}
