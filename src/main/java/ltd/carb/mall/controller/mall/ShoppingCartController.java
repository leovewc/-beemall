 
package ltd.carb.mall.controller.mall;

import ltd.carb.mall.common.Constants;
import ltd.carb.mall.common.CarBMallException;
import ltd.carb.mall.common.ServiceResultEnum;
import ltd.carb.mall.controller.vo.CarBMallShoppingCartItemVO;
import ltd.carb.mall.controller.vo.CarBMallUserVO;
import ltd.carb.mall.entity.CarBMallShoppingCartItem;
import ltd.carb.mall.service.CarBMallShoppingCartService;
import ltd.carb.mall.util.Result;
import ltd.carb.mall.util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ShoppingCartController {

    @Resource
    private CarBMallShoppingCartService carBMallShoppingCartService;

    @GetMapping("/shop-cart")
    public String cartListPage(HttpServletRequest request,
                               HttpSession httpSession) {
        CarBMallUserVO user = (CarBMallUserVO) httpSession.getAttribute(Constants.MALL_USER_SESSION_KEY);
        int itemsTotal = 0;
        int priceTotal = 0;
        List<CarBMallShoppingCartItemVO> myShoppingCartItems = carBMallShoppingCartService.getMyShoppingCartItems(user.getUserId());
        if (!CollectionUtils.isEmpty(myShoppingCartItems)) {
            itemsTotal = myShoppingCartItems.stream().mapToInt(CarBMallShoppingCartItemVO::getCarsCount).sum();
            if (itemsTotal < 1) {
                CarBMallException.fail("cannot be empty");
            }

            for (CarBMallShoppingCartItemVO carBMallShoppingCartItemVO : myShoppingCartItems) {
                priceTotal += carBMallShoppingCartItemVO.getCarsCount() * carBMallShoppingCartItemVO.getSellingPrice();
            }
            if (priceTotal < 1) {
                CarBMallException.fail("Abnormal prices");
            }
        }
        request.setAttribute("itemsTotal", itemsTotal);
        request.setAttribute("priceTotal", priceTotal);
        request.setAttribute("myShoppingCartItems", myShoppingCartItems);
        return "mall/cart";
    }

    @PostMapping("/shop-cart")
    @ResponseBody
    public Result saveCarBMallShoppingCartItem(@RequestBody CarBMallShoppingCartItem carBMallShoppingCartItem,
                                                 HttpSession httpSession) {
        CarBMallUserVO user = (CarBMallUserVO) httpSession.getAttribute(Constants.MALL_USER_SESSION_KEY);
        carBMallShoppingCartItem.setUserId(user.getUserId());
        String saveResult = carBMallShoppingCartService.saveCarBMallCartItem(carBMallShoppingCartItem);
        if (ServiceResultEnum.SUCCESS.getResult().equals(saveResult)) {
            return ResultGenerator.genSuccessResult();
        }
        return ResultGenerator.genFailResult(saveResult);
    }

    @PutMapping("/shop-cart")
    @ResponseBody
    public Result updateCarBMallShoppingCartItem(@RequestBody CarBMallShoppingCartItem carBMallShoppingCartItem,
                                                   HttpSession httpSession) {
        CarBMallUserVO user = (CarBMallUserVO) httpSession.getAttribute(Constants.MALL_USER_SESSION_KEY);
        carBMallShoppingCartItem.setUserId(user.getUserId());
        String updateResult = carBMallShoppingCartService.updateCarBMallCartItem(carBMallShoppingCartItem);
        if (ServiceResultEnum.SUCCESS.getResult().equals(updateResult)) {
            return ResultGenerator.genSuccessResult();
        }
        //fail to edit
        return ResultGenerator.genFailResult(updateResult);
    }

    @DeleteMapping("/shop-cart/{CarBMallShoppingCartItemId}")
    @ResponseBody
    public Result updateCarBMallShoppingCartItem(@PathVariable("CarBMallShoppingCartItemId") Long CarBMallShoppingCartItemId,
                                                   HttpSession httpSession) {
        CarBMallUserVO user = (CarBMallUserVO) httpSession.getAttribute(Constants.MALL_USER_SESSION_KEY);
        Boolean deleteResult = carBMallShoppingCartService.deleteById(CarBMallShoppingCartItemId,user.getUserId());
        if (deleteResult) {
            return ResultGenerator.genSuccessResult();
        }
        //failed to delete
        return ResultGenerator.genFailResult(ServiceResultEnum.OPERATE_ERROR.getResult());
    }

    @GetMapping("/shop-cart/settle")
    public String settlePage(HttpServletRequest request,
                             HttpSession httpSession) {
        int priceTotal = 0;
        CarBMallUserVO user = (CarBMallUserVO) httpSession.getAttribute(Constants.MALL_USER_SESSION_KEY);
        List<CarBMallShoppingCartItemVO> myShoppingCartItems = carBMallShoppingCartService.getMyShoppingCartItems(user.getUserId());
        if (CollectionUtils.isEmpty(myShoppingCartItems)) {
            return "/shop-cart";
        } else {
            for (CarBMallShoppingCartItemVO carBMallShoppingCartItemVO : myShoppingCartItems) {
                priceTotal += carBMallShoppingCartItemVO.getCarsCount() * carBMallShoppingCartItemVO.getSellingPrice();
            }
            if (priceTotal < 1) {
                CarBMallException.fail("Abnormal prices");
            }
        }
        request.setAttribute("priceTotal", priceTotal);
        request.setAttribute("myShoppingCartItems", myShoppingCartItems);
        return "mall/order-settle";
    }
}
