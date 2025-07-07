 
package ltd.carb.mall.dao;

import ltd.carb.mall.entity.CarBMallOrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CarBMallOrderItemMapper {
    int deleteByPrimaryKey(Long orderItemId);

    int insert(CarBMallOrderItem record);

    int insertSelective(CarBMallOrderItem record);

    CarBMallOrderItem selectByPrimaryKey(Long orderItemId);

    /**
     * id
     *
     * @param orderId
     * @return
     */
    List<CarBMallOrderItem> selectByOrderId(Long orderId);

    /**
     * ids
     *
     * @param orderIds
     * @return
     */
    List<CarBMallOrderItem> selectByOrderIds(@Param("orderIds") List<Long> orderIds);

    /**
     * insert
     *
     * @param orderItems
     * @return
     */
    int insertBatch(@Param("orderItems") List<CarBMallOrderItem> orderItems);

    int updateByPrimaryKeySelective(CarBMallOrderItem record);

    int updateByPrimaryKey(CarBMallOrderItem record);
}