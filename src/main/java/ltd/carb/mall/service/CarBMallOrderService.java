 
package ltd.carb.mall.service;

import ltd.carb.mall.controller.vo.CarBMallOrderDetailVO;
import ltd.carb.mall.controller.vo.CarBMallOrderItemVO;
import ltd.carb.mall.controller.vo.CarBMallShoppingCartItemVO;
import ltd.carb.mall.controller.vo.CarBMallUserVO;
import ltd.carb.mall.entity.CarBMallOrder;
import ltd.carb.mall.util.PageQueryUtil;
import ltd.carb.mall.util.PageResult;

import java.util.List;

public interface CarBMallOrderService {
    /**
     *
     * @param pageUtil
     * @return
     */
    PageResult getCarBMallOrdersPage(PageQueryUtil pageUtil);

    /**
     *
     * @param carBMallOrder
     * @return
     */
    String updateOrderInfo(CarBMallOrder carBMallOrder);

    /**
     *
     * @param ids
     * @return
     */
    String checkDone(Long[] ids);

    /**
     *
     * @param ids
     * @return
     */
    String checkOut(Long[] ids);

    /**
     *
     * @param ids
     * @return
     */
    String closeOrder(Long[] ids);

    /**
     *
     * @param user
     * @param myShoppingCartItems
     * @return
     */
    String saveOrder(CarBMallUserVO user, List<CarBMallShoppingCartItemVO> myShoppingCartItems);

    /**
     *
     * @param orderNo
     * @param userId
     * @return
     */
    CarBMallOrderDetailVO getOrderDetailByOrderNo(String orderNo, Long userId);

    /**
     *
     * @param orderNo
     * @return
     */
    CarBMallOrder getCarBMallOrderByOrderNo(String orderNo);

    /**
     *
     * @param pageUtil
     * @return
     */
    PageResult getMyOrders(PageQueryUtil pageUtil);

    /**
     *
     * @param orderNo
     * @param userId
     * @return
     */
    String cancelOrder(String orderNo, Long userId);

    /**
     *
     * @param orderNo
     * @param userId
     * @return
     */
    String finishOrder(String orderNo, Long userId);

    String paySuccess(String orderNo, int payType);

    List<CarBMallOrderItemVO> getOrderItems(Long id);
}
