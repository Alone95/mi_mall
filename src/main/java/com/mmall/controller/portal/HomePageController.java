package com.mmall.controller.portal;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.IRecommendService;
import com.mmall.util.CookieUtil;
import com.mmall.util.JsonUtil;
import com.mmall.util.RedisShardedPoolUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author alone95
 * @date 2018/5/18
 */
@Controller
@RequestMapping("/index/")
public class HomePageController {

    @Autowired
    private IRecommendService iRecommendService;

    @RequestMapping(value = "getProductRecommend.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getProductRecommend(HttpServletRequest httpServletRequest,Integer categoryId,Integer num){
        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
        if(StringUtils.isEmpty(loginToken)){
            return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
        }
        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
        User user = JsonUtil.string2Obj(userJsonStr,User.class);
        if(user ==null){
            //用户没有登录，按对应楼层的categoryId进行推荐
            return iRecommendService.productRecommend(0, categoryId, num);
        } else {
            //用户登录，对应楼层的categoryId按照用户浏览记录和搜索记录进行推荐
            return iRecommendService.productRecommend(user.getId(), categoryId, num);
        }
    }
}
