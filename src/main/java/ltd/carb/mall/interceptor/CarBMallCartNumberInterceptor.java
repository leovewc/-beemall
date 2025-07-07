 
package ltd.carb.mall.interceptor;

import ltd.carb.mall.common.Constants;
import ltd.carb.mall.controller.vo.CarBMallUserVO;
import ltd.carb.mall.dao.CarBMallShoppingCartItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class CarBMallCartNumberInterceptor implements HandlerInterceptor {

    @Autowired
    private CarBMallShoppingCartItemMapper carBMallShoppingCartItemMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        if (null != request.getSession() && null != request.getSession().getAttribute(Constants.MALL_USER_SESSION_KEY)) {
            CarBMallUserVO carBMallUserVO = (CarBMallUserVO) request.getSession().getAttribute(Constants.MALL_USER_SESSION_KEY);
            carBMallUserVO.setShopCartItemCount(carBMallShoppingCartItemMapper.selectCountByUserId(carBMallUserVO.getUserId()));
            request.getSession().setAttribute(Constants.MALL_USER_SESSION_KEY, carBMallUserVO);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
