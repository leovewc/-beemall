 
package ltd.carb.mall.controller.admin;

import cn.hutool.captcha.ShearCaptcha;
import ltd.carb.mall.common.CarBMallException;
import ltd.carb.mall.common.Constants;
import ltd.carb.mall.common.IndexConfigTypeEnum;
import ltd.carb.mall.common.ServiceResultEnum;
import ltd.carb.mall.controller.vo.CarBMallIndexCarouselVO;
import ltd.carb.mall.controller.vo.CarBMallIndexCategoryVO;
import ltd.carb.mall.controller.vo.CarBMallIndexConfigCarsVO;
import ltd.carb.mall.entity.AdminUser;
import ltd.carb.mall.service.AdminUserService;
import ltd.carb.mall.service.CarBMallCarouselService;
import ltd.carb.mall.service.CarBMallCategoryService;
import ltd.carb.mall.service.CarBMallIndexConfigService;

import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminUserService adminUserService;
    
    @Resource
    private CarBMallCarouselService carBMallCarouselService;

    @Resource
    private CarBMallIndexConfigService carBMallIndexConfigService;

    @Resource
    private CarBMallCategoryService carBMallCategoryService;
    
    @GetMapping({"/login"})
    public String login() {
        return "admin/login";
    }

    @GetMapping({"/test"})
    public String test() {
        return "admin/test";
    }


    @GetMapping({"", "/", "/index", "/index.html"})
    public String index(HttpServletRequest request) {
        request.setAttribute("path", "index");
        return "admin/index";
    }

    @PostMapping(value = "/login")
    public String login(@RequestParam("userName") String userName,
                        @RequestParam("password") String password,
                        @RequestParam("verifyCode") String verifyCode,
                        HttpSession session) {
        if (!StringUtils.hasText(verifyCode)) {
            session.setAttribute("errorMsg", "verification code must be filled");
            return "admin/login";
        }
        if (!StringUtils.hasText(userName) || !StringUtils.hasText(password)) {
            session.setAttribute("errorMsg", "Username or password cannot be empty");
            return "admin/login";
        }
        ShearCaptcha shearCaptcha = (ShearCaptcha) session.getAttribute("verifyCode");
        if (shearCaptcha == null || !shearCaptcha.verify(verifyCode)) {
            session.setAttribute("errorMsg", "Verification code error");
            return "admin/login";
        }
        AdminUser adminUser = adminUserService.login(userName, password);
        if (adminUser != null) {
            session.setAttribute("loginUser", adminUser.getNickName());
            session.setAttribute("loginUserId", adminUser.getAdminUserId());
            //2 hours
            //session.setMaxInactiveInterval(60 * 60 * 2);
            return "redirect:/admin/index";
        } else {
            session.setAttribute("errorMsg", "fail to login");
            return "admin/login";
        }
    }

    @GetMapping("/profile")
    public String profile(HttpServletRequest request) {
        Integer loginUserId = (int) request.getSession().getAttribute("loginUserId");
        AdminUser adminUser = adminUserService.getUserDetailById(loginUserId);
        if (adminUser == null) {
            return "admin/login";
        }
        request.setAttribute("path", "profile");
        request.setAttribute("loginUserName", adminUser.getLoginUserName());
        request.setAttribute("nickName", adminUser.getNickName());
        return "admin/profile";
    }

    @PostMapping("/profile/password")
    @ResponseBody
    public String passwordUpdate(HttpServletRequest request, @RequestParam("originalPassword") String originalPassword,
                                 @RequestParam("newPassword") String newPassword) {
        if (!StringUtils.hasText(originalPassword) || !StringUtils.hasText(newPassword)) {
            return "Parameter cannot be empty";
        }
        Integer loginUserId = (int) request.getSession().getAttribute("loginUserId");
        if (adminUserService.updatePassword(loginUserId, originalPassword, newPassword)) {
            request.getSession().removeAttribute("loginUserId");
            request.getSession().removeAttribute("loginUser");
            request.getSession().removeAttribute("errorMsg");
            return ServiceResultEnum.SUCCESS.getResult();
        } else {
            return "fail to edit";
        }
    }

    @PostMapping("/profile/name")
    @ResponseBody
    public String nameUpdate(HttpServletRequest request, @RequestParam("loginUserName") String loginUserName,
                             @RequestParam("nickName") String nickName) {
        if (!StringUtils.hasText(loginUserName) || !StringUtils.hasText(nickName)) {
            return "Parameter cannot be empty";
        }
        Integer loginUserId = (int) request.getSession().getAttribute("loginUserId");
        if (adminUserService.updateName(loginUserId, loginUserName, nickName)) {
            return ServiceResultEnum.SUCCESS.getResult();
        } else {
            return "fail to edit";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("loginUserId");
        request.getSession().removeAttribute("loginUser");
        request.getSession().removeAttribute("errorMsg");
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
}
