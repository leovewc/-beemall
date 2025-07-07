 
package ltd.carb.mall.service;

import ltd.carb.mall.controller.vo.CarBMallShoppingCartItemVO;
import ltd.carb.mall.entity.CarBMallShoppingCartItem;

import java.util.List;

public interface CarBMallShoppingCartService {

    /**
     *
     * @param carBMallShoppingCartItem
     * @return
     */
    String saveCarBMallCartItem(CarBMallShoppingCartItem carBMallShoppingCartItem);

    /**
     *
     * @param carBMallShoppingCartItem
     * @return
     */
    String updateCarBMallCartItem(CarBMallShoppingCartItem carBMallShoppingCartItem);

    /**
     *
     * @param CarBMallShoppingCartItemId
     * @return
     */
    CarBMallShoppingCartItem getCarBMallCartItemById(Long CarBMallShoppingCartItemId);

    /**
     *
     * @param shoppingCartItemId
     * @param userId
     * @return
     */
    Boolean deleteById(Long shoppingCartItemId, Long userId);

    /**
     *
     * @param CarBMallUserId
     * @return
     */
    List<CarBMallShoppingCartItemVO> getMyShoppingCartItems(Long CarBMallUserId);
}
