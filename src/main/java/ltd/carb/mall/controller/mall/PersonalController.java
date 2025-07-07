 
package ltd.carb.mall.controller.mall;

import cn.hutool.captcha.ShearCaptcha;
import ltd.carb.mall.common.CarBMallException;
import ltd.carb.mall.common.Constants;
import ltd.carb.mall.common.IndexConfigTypeEnum;
import ltd.carb.mall.common.ServiceResultEnum;
import ltd.carb.mall.controller.vo.CarBMallIndexCarouselVO;
import ltd.carb.mall.controller.vo.CarBMallIndexCategoryVO;
import ltd.carb.mall.controller.vo.CarBMallIndexConfigCarsVO;
import ltd.carb.mall.controller.vo.CarBMallUserVO;
import ltd.carb.mall.entity.MallUser;
import ltd.carb.mall.service.CarBMallCarouselService;
import ltd.carb.mall.service.CarBMallCategoryService;
import ltd.carb.mall.service.CarBMallIndexConfigService;
import ltd.carb.mall.service.CarBMallUserService;
import ltd.carb.mall.util.MD5Util;
import ltd.carb.mall.util.Result;
import ltd.carb.mall.util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class PersonalController {

    @Resource
    private CarBMallUserService carBMallUserService;

    @Resource
    private CarBMallCarouselService carBMallCarouselService;

    @Resource
    private CarBMallIndexConfigService carBMallIndexConfigService;

    @Resource
    private CarBMallCategoryService carBMallCategoryService;

    @GetMapping("/personal")
    public String personalPage(HttpServletRequest request,
                               HttpSession httpSession) {
        request.setAttribute("path", "personal");
        return "mall/personal";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,HttpSession httpSession) {
        httpSession.removeAttribute(Constants.MALL_USER_SESSION_KEY);
        List<CarBMallIndexCategoryVO> categories = carBMallCategoryService.getCategoriesForIndex();
        if (CollectionUtils.isEmpty(categories)) {
            CarBMallException.fail("Imperfect classification data");
        }
        List<CarBMallIndexCarouselVO> carousels = carBMallCarouselService.getCarouselsForIndex(Constants.INDEX_CAROUSEL_NUMBER);
        List<CarBMallIndexConfigCarsVO> hotCars = carBMallIndexConfigService.getConfigCarsForIndex(IndexConfigTypeEnum.INDEX_CARS_HOT.getType(), Constants.INDEX_CARS_HOT_NUMBER);
        List<CarBMallIndexConfigCarsVO> newCars = carBMallIndexConfigService.getConfigCarsForIndex(IndexConfigTypeEnum.INDEX_CARS_NEW.getType(), Constants.INDEX_CARS_NEW_NUMBER);
        List<CarBMallIndexConfigCarsVO> recommendCars = carBMallIndexConfigService.getConfigCarsForIndex(IndexConfigTypeEnum.INDEX_CARS_RECOMMOND.getType(), Constants.INDEX_CARS_RECOMMOND_NUMBER);
        request.setAttribute("categories", categories);
        request.setAttribute("carousels", carousels);
        request.setAttribute("hotCars", hotCars);
        request.setAttribute("newCars", newCars);
        request.setAttribute("recommendCars", recommendCars); 
        return "mall/index";
    }

    @GetMapping({"/login", "login.html"})
    public String loginPage() {
        return "mall/login";
    }

    @GetMapping({"/register", "register.html"})
    public String registerPage() {
        return "mall/register";
    }

    @GetMapping("/personal/addresses")
    public String addressesPage() {
        return "mall/addresses";
    }

    @PostMapping("/login")
    @ResponseBody
    public Result login(@RequestParam("loginName") String loginName,
                        @RequestParam("verifyCode") String verifyCode,
                        @RequestParam("password") String password,
                        HttpSession httpSession) {
        if (!StringUtils.hasText(loginName)) {
            return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_NAME_NULL.getResult());
        }
        if (!StringUtils.hasText(password)) {
            return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_PASSWORD_NULL.getResult());
        }
        if (!StringUtils.hasText(verifyCode)) {
            return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_VERIFY_CODE_NULL.getResult());
        }
        ShearCaptcha shearCaptcha = (ShearCaptcha) httpSession.getAttribute(Constants.MALL_VERIFY_CODE_KEY);

        if (shearCaptcha == null || !shearCaptcha.verify(verifyCode)) {
            return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_VERIFY_CODE_ERROR.getResult());
        }
        String loginResult = carBMallUserService.login(loginName, MD5Util.MD5Encode(password, "UTF-8"), httpSession);
        if (ServiceResultEnum.SUCCESS.getResult().equals(loginResult)) {
            httpSession.removeAttribute(Constants.MALL_VERIFY_CODE_KEY);
            return ResultGenerator.genSuccessResult();
        }
        return ResultGenerator.genFailResult(loginResult);
    }

    @PostMapping("/register")
    @ResponseBody
    public Result register(@RequestParam("loginName") String loginName,
                           @RequestParam("verifyCode") String verifyCode,
                           @RequestParam("password") String password,
                           HttpSession httpSession) {
        if (!StringUtils.hasText(loginName)) {
            return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_NAME_NULL.getResult());
        }
        if (!StringUtils.hasText(password)) {
            return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_PASSWORD_NULL.getResult());
        }
        if (!StringUtils.hasText(verifyCode)) {
            return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_VERIFY_CODE_NULL.getResult());
        }
        ShearCaptcha shearCaptcha = (ShearCaptcha) httpSession.getAttribute(Constants.MALL_VERIFY_CODE_KEY);
        if (shearCaptcha == null || !shearCaptcha.verify(verifyCode)) {
            return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_VERIFY_CODE_ERROR.getResult());
        }
        String registerResult = carBMallUserService.register(loginName, password);

        if (ServiceResultEnum.SUCCESS.getResult().equals(registerResult)) {

            httpSession.removeAttribute(Constants.MALL_VERIFY_CODE_KEY);
            return ResultGenerator.genSuccessResult();
        }

        return ResultGenerator.genFailResult(registerResult);
    }
    
    
    
    @PostMapping("/personal/updateInfo")
    @ResponseBody
    public Result updateInfo(@RequestBody MallUser mallUser, HttpSession httpSession) {
        CarBMallUserVO mallUserTemp = carBMallUserService.updateUserInfo(mallUser, httpSession);
        if (mallUserTemp == null) {
            Result result = ResultGenerator.genFailResult("fail to edit");
            return result;
        } else {
            Result result = ResultGenerator.genSuccessResult();
            return result;
        }
    }
}
