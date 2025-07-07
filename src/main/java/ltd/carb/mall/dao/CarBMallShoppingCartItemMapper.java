 
package ltd.carb.mall.dao;

import ltd.carb.mall.entity.CarBMallShoppingCartItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CarBMallShoppingCartItemMapper {
    int deleteByPrimaryKey(Long cartItemId);

    int insert(CarBMallShoppingCartItem record);

    int insertSelective(CarBMallShoppingCartItem record);

    CarBMallShoppingCartItem selectByPrimaryKey(Long cartItemId);

    CarBMallShoppingCartItem selectByUserIdAndCarsId(@Param("CarBMallUserId") Long CarBMallUserId, @Param("carsId") Long carsId);

    List<CarBMallShoppingCartItem> selectByUserId(@Param("CarBMallUserId") Long CarBMallUserId, @Param("number") int number);

    int selectCountByUserId(Long CarBMallUserId);

    int updateByPrimaryKeySelective(CarBMallShoppingCartItem record);

    int updateByPrimaryKey(CarBMallShoppingCartItem record);

    int deleteBatch(List<Long> ids);
}