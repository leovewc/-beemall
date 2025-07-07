 
package ltd.carb.mall.controller.mall;

import ltd.carb.mall.common.Constants;
import ltd.carb.mall.common.CarBMallException;
import ltd.carb.mall.common.CarBMallOrderStatusEnum;
import ltd.carb.mall.common.ServiceResultEnum;
import ltd.carb.mall.controller.vo.CarBMallOrderDetailVO;
import ltd.carb.mall.controller.vo.CarBMallShoppingCartItemVO;
import ltd.carb.mall.controller.vo.CarBMallUserVO;
import ltd.carb.mall.entity.CarBMallOrder;
import ltd.carb.mall.service.CarBMallOrderService;
import ltd.carb.mall.service.CarBMallShoppingCartService;
import ltd.carb.mall.util.PageQueryUtil;
import ltd.carb.mall.util.Result;
import ltd.carb.mall.util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class OrderController {

    @Resource
    private CarBMallShoppingCartService carBMallShoppingCartService;
    @Resource
    private CarBMallOrderService carBMallOrderService;

    @GetMapping("/orders/{orderNo}")
    public String orderDetailPage(HttpServletRequest request, @PathVariable("orderNo") String orderNo, HttpSession httpSession) {
        CarBMallUserVO user = (CarBMallUserVO) httpSession.getAttribute(Constants.MALL_USER_SESSION_KEY);
        CarBMallOrderDetailVO orderDetailVO = carBMallOrderService.getOrderDetailByOrderNo(orderNo, user.getUserId());
        request.setAttribute("orderDetailVO", orderDetailVO);
        return "mall/order-detail";
    }

    @GetMapping("/orders")
    public String orderListPage(@RequestParam Map<String, Object> params, HttpServletRequest request, HttpSession httpSession) {
        CarBMallUserVO user = (CarBMallUserVO) httpSession.getAttribute(Constants.MALL_USER_SESSION_KEY);
        params.put("userId", user.getUserId());
        if (ObjectUtils.isEmpty(params.get("page"))) {
            params.put("page", 1);
        }
        params.put("limit", Constants.ORDER_SEARCH_PAGE_LIMIT);
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        request.setAttribute("orderPageResult", carBMallOrderService.getMyOrders(pageUtil));
        request.setAttribute("path", "orders");
        return "mall/my-orders";
    }

    @GetMapping("/saveOrder")
    public String saveOrder(HttpSession httpSession) {
        CarBMallUserVO user = (CarBMallUserVO) httpSession.getAttribute(Constants.MALL_USER_SESSION_KEY);
        List<CarBMallShoppingCartItemVO> myShoppingCartItems = carBMallShoppingCartService.getMyShoppingCartItems(user.getUserId());
        if (!StringUtils.hasText(user.getAddress().trim())) {
            CarBMallException.fail(ServiceResultEnum.NULL_ADDRESS_ERROR.getResult());
        }
        if (CollectionUtils.isEmpty(myShoppingCartItems)) {
            CarBMallException.fail(ServiceResultEnum.SHOPPING_ITEM_ERROR.getResult());
        }
        String saveOrderResult = carBMallOrderService.saveOrder(user, myShoppingCartItems);
        return "redirect:/orders/" + saveOrderResult;
    }

    @PutMapping("/orders/{orderNo}/cancel")
    @ResponseBody
    public Result cancelOrder(@PathVariable("orderNo") String orderNo, HttpSession httpSession) {
        CarBMallUserVO user = (CarBMallUserVO) httpSession.getAttribute(Constants.MALL_USER_SESSION_KEY);
        String cancelOrderResult = carBMallOrderService.cancelOrder(orderNo, user.getUserId());
        if (ServiceResultEnum.SUCCESS.getResult().equals(cancelOrderResult)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(cancelOrderResult);
        }
    }

    @PutMapping("/orders/{orderNo}/finish")
    @ResponseBody
    public Result finishOrder(@PathVariable("orderNo") String orderNo, HttpSession httpSession) {
        CarBMallUserVO user = (CarBMallUserVO) httpSession.getAttribute(Constants.MALL_USER_SESSION_KEY);
        String finishOrderResult = carBMallOrderService.finishOrder(orderNo, user.getUserId());
        if (ServiceResultEnum.SUCCESS.getResult().equals(finishOrderResult)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(finishOrderResult);
        }
    }

    @GetMapping("/selectPayType")
    public String selectPayType(HttpServletRequest request, @RequestParam("orderNo") String orderNo, HttpSession httpSession) {
        CarBMallUserVO user = (CarBMallUserVO) httpSession.getAttribute(Constants.MALL_USER_SESSION_KEY);
        CarBMallOrder carBMallOrder = carBMallOrderService.getCarBMallOrderByOrderNo(orderNo);
        if (!user.getUserId().equals(carBMallOrder.getUserId())) {
            CarBMallException.fail(ServiceResultEnum.NO_PERMISSION_ERROR.getResult());
        }
        if (carBMallOrder.getOrderStatus().intValue() != CarBMallOrderStatusEnum.ORDER_PRE_PAY.getOrderStatus()) {
            CarBMallException.fail(ServiceResultEnum.ORDER_STATUS_ERROR.getResult());
        }
        request.setAttribute("orderNo", orderNo);
        request.setAttribute("totalPrice", carBMallOrder.getTotalPrice());
        return "mall/pay-select";
    }

    @GetMapping("/payPage")
    public String payOrder(HttpServletRequest request, @RequestParam("orderNo") String orderNo, HttpSession httpSession, @RequestParam("payType") int payType) {
        CarBMallUserVO user = (CarBMallUserVO) httpSession.getAttribute(Constants.MALL_USER_SESSION_KEY);
        CarBMallOrder carBMallOrder = carBMallOrderService.getCarBMallOrderByOrderNo(orderNo);
        if (!user.getUserId().equals(carBMallOrder.getUserId())) {
            CarBMallException.fail(ServiceResultEnum.NO_PERMISSION_ERROR.getResult());
        }
        if (carBMallOrder.getOrderStatus().intValue() != CarBMallOrderStatusEnum.ORDER_PRE_PAY.getOrderStatus()) {
            CarBMallException.fail(ServiceResultEnum.ORDER_STATUS_ERROR.getResult());
        }
        request.setAttribute("orderNo", orderNo);
        request.setAttribute("totalPrice", carBMallOrder.getTotalPrice());
        if (payType == 1) {
            return "mall/TNG";
        } else {
            return "mall/Grab pay";
        }
    }

    @GetMapping("/paySuccess")
    @ResponseBody
    public Result paySuccess(@RequestParam("orderNo") String orderNo, @RequestParam("payType") int payType) {
        String payResult = carBMallOrderService.paySuccess(orderNo, payType);
        if (ServiceResultEnum.SUCCESS.getResult().equals(payResult)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(payResult);
        }
    }

}
