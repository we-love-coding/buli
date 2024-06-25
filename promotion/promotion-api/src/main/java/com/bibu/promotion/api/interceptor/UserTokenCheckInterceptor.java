package com.bibu.promotion.api.interceptor;

import com.bibu.promotion.domain.manager.UserManager;
import com.bibu.user.facade.response.UserInfoResp;
import com.bibu.user.feign.UserClient;
import com.bibu.user.feign.request.UserTokenRemoteReq;
import com.x.common.context.UserInfoContext;
import com.x.common.context.dto.UserInfoDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * Author XY
 * Date 2024/5/6 下午8:53
 */
@Component
public class UserTokenCheckInterceptor implements HandlerInterceptor {

    @Autowired
    private UserManager userManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if (StringUtils.isBlank(token)) {
            return false;
        }

        UserTokenRemoteReq remoteReq = new UserTokenRemoteReq();
        remoteReq.setToken(token);
        UserInfoResp userInfoResp = userManager.findUserInfoByRPC(remoteReq);
        if (Objects.isNull(userInfoResp)) {
            return false;
        }

        // 多设备token不同    每次修改，删除都要保证缓存中的数据发生变化
        // token在，数据库不在 每次修改，删除都要保证缓存中的数据发生变化
        // 数据库在，token不在 每次修改，删除都要保证缓存中的数据发生变化
        // 每次查数据库，并发高 使用高频发框架应对
        Long userId = userInfoResp.getUserId();
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setUserId(userId);
        userInfoDTO.setPhone(userInfoResp.getPhone());
        userInfoDTO.setEmail(userInfoResp.getEmail());
        userInfoDTO.setNickName(userInfoResp.getNickName());
        userInfoDTO.setGender(userInfoResp.getGender());
        UserInfoContext.instance().setUserInfoDTO(userInfoDTO);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserInfoContext.remove();
    }
}
