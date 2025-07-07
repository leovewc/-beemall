 
package ltd.carb.mall.service.impl;

import ltd.carb.mall.common.Constants;
import ltd.carb.mall.common.ServiceResultEnum;
import ltd.carb.mall.controller.vo.CarBMallShoppingCartItemVO;
import ltd.carb.mall.dao.CarBMallCarsMapper;
import ltd.carb.mall.dao.CarBMallShoppingCartItemMapper;
import ltd.carb.mall.entity.CarBMallCars;
import ltd.carb.mall.entity.CarBMallShoppingCartItem;
import ltd.carb.mall.service.CarBMallShoppingCartService;
import ltd.carb.mall.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CarBMallShoppingCartServiceImpl implements CarBMallShoppingCartService {

    @Autowired
    private CarBMallShoppingCartItemMapper carBMallShoppingCartItemMapper;

    @Autowired
    private CarBMallCarsMapper carBMallCarsMapper;

    @Override
    public String saveCarBMallCartItem(CarBMallShoppingCartItem carBMallShoppingCartItem) {
        CarBMallShoppingCartItem temp = carBMallShoppingCartItemMapper.selectByUserIdAndCarsId(carBMallShoppingCartItem.getUserId(), carBMallShoppingCartItem.getCarsId());
        if (temp != null) {
            temp.setCarsCount(carBMallShoppingCartItem.getCarsCount());
            return updateCarBMallCartItem(temp);
        }
        CarBMallCars carBMallCars = carBMallCarsMapper.selectByPrimaryKey(carBMallShoppingCartItem.getCarsId());
        if (carBMallCars == null) {
            return ServiceResultEnum.CARS_NOT_EXIST.getResult();
        }
        int totalItem = carBMallShoppingCartItemMapper.selectCountByUserId(carBMallShoppingCartItem.getUserId()) + 1;
        if (carBMallShoppingCartItem.getCarsCount() > Constants.SHOPPING_CART_ITEM_LIMIT_NUMBER) {
            return ServiceResultEnum.SHOPPING_CART_ITEM_LIMIT_NUMBER_ERROR.getResult();
        }
        if (totalItem > Constants.SHOPPING_CART_ITEM_TOTAL_NUMBER) {
            return ServiceResultEnum.SHOPPING_CART_ITEM_TOTAL_NUMBER_ERROR.getResult();
        }
        if (carBMallShoppingCartItemMapper.insertSelective(carBMallShoppingCartItem) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public String updateCarBMallCartItem(CarBMallShoppingCartItem carBMallShoppingCartItem) {
        CarBMallShoppingCartItem carBMallShoppingCartItemUpdate = carBMallShoppingCartItemMapper.selectByPrimaryKey(carBMallShoppingCartItem.getCartItemId());
        if (carBMallShoppingCartItemUpdate == null) {
            return ServiceResultEnum.DATA_NOT_EXIST.getResult();
        }
        if (carBMallShoppingCartItem.getCarsCount() > Constants.SHOPPING_CART_ITEM_LIMIT_NUMBER) {
            return ServiceResultEnum.SHOPPING_CART_ITEM_LIMIT_NUMBER_ERROR.getResult();
        }
        //dif in cartItem.userId
        if (!carBMallShoppingCartItemUpdate.getUserId().equals(carBMallShoppingCartItem.getUserId())) {
            return ServiceResultEnum.NO_PERMISSION_ERROR.getResult();
        }
        if (carBMallShoppingCartItem.getCarsCount().equals(carBMallShoppingCartItemUpdate.getCarsCount())) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        carBMallShoppingCartItemUpdate.setCarsCount(carBMallShoppingCartItem.getCarsCount());
        carBMallShoppingCartItemUpdate.setUpdateTime(new Date());
        if (carBMallShoppingCartItemMapper.updateByPrimaryKeySelective(carBMallShoppingCartItemUpdate) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public CarBMallShoppingCartItem getCarBMallCartItemById(Long CarBMallShoppingCartItemId) {
        return carBMallShoppingCartItemMapper.selectByPrimaryKey(CarBMallShoppingCartItemId);
    }

    @Override
    public Boolean deleteById(Long shoppingCartItemId, Long userId) {
        CarBMallShoppingCartItem carBMallShoppingCartItem = carBMallShoppingCartItemMapper.selectByPrimaryKey(shoppingCartItemId);
        if (carBMallShoppingCartItem == null) {
            return false;
        }
        if (!userId.equals(carBMallShoppingCartItem.getUserId())) {
            return false;
        }
        return carBMallShoppingCartItemMapper.deleteByPrimaryKey(shoppingCartItemId) > 0;
    }

    @Override
    public List<CarBMallShoppingCartItemVO> getMyShoppingCartItems(Long CarBMallUserId) {
        List<CarBMallShoppingCartItemVO> carBMallShoppingCartItemVOS = new ArrayList<>();
        List<CarBMallShoppingCartItem> carBMallShoppingCartItems = carBMallShoppingCartItemMapper.selectByUserId(CarBMallUserId, Constants.SHOPPING_CART_ITEM_TOTAL_NUMBER);
        if (!CollectionUtils.isEmpty(carBMallShoppingCartItems)) {
            List<Long> CarBMallCarsIds = carBMallShoppingCartItems.stream().map(CarBMallShoppingCartItem::getCarsId).collect(Collectors.toList());
            List<CarBMallCars> carBMallCars = carBMallCarsMapper.selectByPrimaryKeys(CarBMallCarsIds);
            Map<Long, CarBMallCars> CarBMallCarsMap = new HashMap<>();
            if (!CollectionUtils.isEmpty(carBMallCars)) {
                CarBMallCarsMap = carBMallCars.stream().collect(Collectors.toMap(CarBMallCars::getCarsId, Function.identity(), (entity1, entity2) -> entity1));
            }
            for (CarBMallShoppingCartItem carBMallShoppingCartItem : carBMallShoppingCartItems) {
                CarBMallShoppingCartItemVO carBMallShoppingCartItemVO = new CarBMallShoppingCartItemVO();
                BeanUtil.copyProperties(carBMallShoppingCartItem, carBMallShoppingCartItemVO);
                if (CarBMallCarsMap.containsKey(carBMallShoppingCartItem.getCarsId())) {
                    CarBMallCars carBMallCarsTemp = CarBMallCarsMap.get(carBMallShoppingCartItem.getCarsId());
                    carBMallShoppingCartItemVO.setCarsCoverImg(carBMallCarsTemp.getCarsCoverImg());
                    String carsName = carBMallCarsTemp.getCarsName();
                    if (carsName.length() > 28) {
                        carsName = carsName.substring(0, 28) + "...";
                    }
                    carBMallShoppingCartItemVO.setCarsName(carsName);
                    carBMallShoppingCartItemVO.setSellingPrice(carBMallCarsTemp.getSellingPrice());
                    carBMallShoppingCartItemVOS.add(carBMallShoppingCartItemVO);
                }
            }
        }
        return carBMallShoppingCartItemVOS;
    }
}
