 
package ltd.carb.mall.dao;

import ltd.carb.mall.entity.CarBMallOrder;
import ltd.carb.mall.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CarBMallOrderMapper {
    int deleteByPrimaryKey(Long orderId);

    int insert(CarBMallOrder record);

    int insertSelective(CarBMallOrder record);

    CarBMallOrder selectByPrimaryKey(Long orderId);

    CarBMallOrder selectByOrderNo(String orderNo);

    int updateByPrimaryKeySelective(CarBMallOrder record);

    int updateByPrimaryKey(CarBMallOrder record);

    List<CarBMallOrder> findCarBMallOrderList(PageQueryUtil pageUtil);

    int getTotalCarBMallOrders(PageQueryUtil pageUtil);

    List<CarBMallOrder> selectByPrimaryKeys(@Param("orderIds") List<Long> orderIds);

    int checkOut(@Param("orderIds") List<Long> orderIds);

    int closeOrder(@Param("orderIds") List<Long> orderIds, @Param("orderStatus") int orderStatus);

    int checkDone(@Param("orderIds") List<Long> asList);
}