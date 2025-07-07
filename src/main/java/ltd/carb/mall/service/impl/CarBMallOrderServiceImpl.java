 
package ltd.carb.mall.service.impl;

import ltd.carb.mall.common.*;
import ltd.carb.mall.controller.vo.*;
import ltd.carb.mall.dao.CarBMallCarsMapper;
import ltd.carb.mall.dao.CarBMallOrderItemMapper;
import ltd.carb.mall.dao.CarBMallOrderMapper;
import ltd.carb.mall.dao.CarBMallShoppingCartItemMapper;
import ltd.carb.mall.entity.CarBMallCars;
import ltd.carb.mall.entity.CarBMallOrder;
import ltd.carb.mall.entity.CarBMallOrderItem;
import ltd.carb.mall.entity.StockNumDTO;
import ltd.carb.mall.service.CarBMallOrderService;
import ltd.carb.mall.util.BeanUtil;
import ltd.carb.mall.util.NumberUtil;
import ltd.carb.mall.util.PageQueryUtil;
import ltd.carb.mall.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
public class CarBMallOrderServiceImpl implements CarBMallOrderService {

    @Autowired
    private CarBMallOrderMapper carBMallOrderMapper;
    @Autowired
    private CarBMallOrderItemMapper carBMallOrderItemMapper;
    @Autowired
    private CarBMallShoppingCartItemMapper carBMallShoppingCartItemMapper;
    @Autowired
    private CarBMallCarsMapper carBMallCarsMapper;

    @Override
    public PageResult getCarBMallOrdersPage(PageQueryUtil pageUtil) {
        List<CarBMallOrder> carBMallOrders = carBMallOrderMapper.findCarBMallOrderList(pageUtil);
        int total = carBMallOrderMapper.getTotalCarBMallOrders(pageUtil);
        PageResult pageResult = new PageResult(carBMallOrders, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    @Transactional
    public String updateOrderInfo(CarBMallOrder carBMallOrder) {
        CarBMallOrder temp = carBMallOrderMapper.selectByPrimaryKey(carBMallOrder.getOrderId());
        if (temp != null && temp.getOrderStatus() >= 0 && temp.getOrderStatus() < 3) {
            temp.setTotalPrice(carBMallOrder.getTotalPrice());
            temp.setUserAddress(carBMallOrder.getUserAddress());
            temp.setUpdateTime(new Date());
            if (carBMallOrderMapper.updateByPrimaryKeySelective(temp) > 0) {
                return ServiceResultEnum.SUCCESS.getResult();
            }
            return ServiceResultEnum.DB_ERROR.getResult();
        }
        return ServiceResultEnum.DATA_NOT_EXIST.getResult();
    }

    @Override
    @Transactional
    public String checkDone(Long[] ids) {
        List<CarBMallOrder> orders = carBMallOrderMapper.selectByPrimaryKeys(Arrays.asList(ids));
        String errorOrderNos = "";
        if (!CollectionUtils.isEmpty(orders)) {
            for (CarBMallOrder carBMallOrder : orders) {
                if (carBMallOrder.getIsDeleted() == 1) {
                    errorOrderNos += carBMallOrder.getOrderNo() + " ";
                    continue;
                }
                if (carBMallOrder.getOrderStatus() != 1) {
                    errorOrderNos += carBMallOrder.getOrderNo() + " ";
                }
            }
            if (!StringUtils.hasText(errorOrderNos)) {
                if (carBMallOrderMapper.checkDone(Arrays.asList(ids)) > 0) {
                    return ServiceResultEnum.SUCCESS.getResult();
                } else {
                    return ServiceResultEnum.DB_ERROR.getResult();
                }
            } else {
                if (errorOrderNos.length() > 0 && errorOrderNos.length() < 100) {
                    return errorOrderNos + "The order status is not payment successful and the delivery operation cannot be performed";
                } else {
                    return "You have selected too many orders whose status is not successfully paid, and the delivery completion operation cannot be performed.";
                }
            }
        }
        //No data found
        return ServiceResultEnum.DATA_NOT_EXIST.getResult();
    }

    @Override
    @Transactional
    public String checkOut(Long[] ids) {
        List<CarBMallOrder> orders = carBMallOrderMapper.selectByPrimaryKeys(Arrays.asList(ids));
        String errorOrderNos = "";
        if (!CollectionUtils.isEmpty(orders)) {
            for (CarBMallOrder carBMallOrder : orders) {
                if (carBMallOrder.getIsDeleted() == 1) {
                    errorOrderNos += carBMallOrder.getOrderNo() + " ";
                    continue;
                }
                if (carBMallOrder.getOrderStatus() != 1 && carBMallOrder.getOrderStatus() != 2) {
                    errorOrderNos += carBMallOrder.getOrderNo() + " ";
                }
            }
            if (!StringUtils.hasText(errorOrderNos)) {
                if (carBMallOrderMapper.checkOut(Arrays.asList(ids)) > 0) {
                    return ServiceResultEnum.SUCCESS.getResult();
                } else {
                    return ServiceResultEnum.DB_ERROR.getResult();
                }
            } else {
                if (errorOrderNos.length() > 0 && errorOrderNos.length() < 100) {
                    return errorOrderNos + "The order status is not payment successful and the delivery operation cannot be performed";
                } else {
                    return "You have selected too many orders whose status is not successfully paid, and the delivery completion operation cannot be performed.";
                }
            }
        }
        //No data found
        return ServiceResultEnum.DATA_NOT_EXIST.getResult();
    }

    @Override
    @Transactional
    public String closeOrder(Long[] ids) {
        List<CarBMallOrder> orders = carBMallOrderMapper.selectByPrimaryKeys(Arrays.asList(ids));
        String errorOrderNos = "";
        if (!CollectionUtils.isEmpty(orders)) {
            for (CarBMallOrder carBMallOrder : orders) {
                // isDeleted=1
                if (carBMallOrder.getIsDeleted() == 1) {
                    errorOrderNos += carBMallOrder.getOrderNo() + " ";
                    continue;
                }
                if (carBMallOrder.getOrderStatus() == 4 || carBMallOrder.getOrderStatus() < 0) {
                    errorOrderNos += carBMallOrder.getOrderNo() + " ";
                }
            }
            if (!StringUtils.hasText(errorOrderNos)) {
                if (carBMallOrderMapper.closeOrder(Arrays.asList(ids), CarBMallOrderStatusEnum.ORDER_CLOSED_BY_JUDGE.getOrderStatus()) > 0 && recoverStockNum(Arrays.asList(ids))) {
                    return ServiceResultEnum.SUCCESS.getResult();
                } else {
                    return ServiceResultEnum.DB_ERROR.getResult();
                }
            } else {
                if (errorOrderNos.length() > 0 && errorOrderNos.length() < 100) {
                    return errorOrderNos + "The order cannot be closed.";
                } else {
                    return "The order you selected cannot be closed.";
                }
            }
        }
        //No data found
        return ServiceResultEnum.DATA_NOT_EXIST.getResult();
    }

    @Override
    @Transactional
    public String saveOrder(CarBMallUserVO user, List<CarBMallShoppingCartItemVO> myShoppingCartItems) {
        List<Long> itemIdList = myShoppingCartItems.stream().map(CarBMallShoppingCartItemVO::getCartItemId).collect(Collectors.toList());
        List<Long> carsIds = myShoppingCartItems.stream().map(CarBMallShoppingCartItemVO::getCarsId).collect(Collectors.toList());
        List<CarBMallCars> carBMallCars = carBMallCarsMapper.selectByPrimaryKeys(carsIds);
        List<CarBMallCars> carsListNotSelling = carBMallCars.stream()
                .filter(CarBMallCarsTemp -> CarBMallCarsTemp.getCarsSellStatus() != Constants.SELL_STATUS_UP)
                .collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(carsListNotSelling)) {
            //carsListNotSelling
            CarBMallException.fail(carsListNotSelling.get(0).getCarsName() + "It has been removed from the shelves and cannot generate orders");
        }
        Map<Long, CarBMallCars> CarBMallCarsMap = carBMallCars.stream().collect(Collectors.toMap(CarBMallCars::getCarsId, Function.identity(), (entity1, entity2) -> entity1));
        for (CarBMallShoppingCartItemVO shoppingCartItemVO : myShoppingCartItems) {
            if (!CarBMallCarsMap.containsKey(shoppingCartItemVO.getCarsId())) {
                CarBMallException.fail(ServiceResultEnum.SHOPPING_ITEM_ERROR.getResult());
            }
            if (shoppingCartItemVO.getCarsCount() > CarBMallCarsMap.get(shoppingCartItemVO.getCarsId()).getStockNum()) {
                CarBMallException.fail(ServiceResultEnum.SHOPPING_ITEM_COUNT_ERROR.getResult());
            }
        }
        if (!CollectionUtils.isEmpty(itemIdList) && !CollectionUtils.isEmpty(carsIds) && !CollectionUtils.isEmpty(carBMallCars)) {
            if (carBMallShoppingCartItemMapper.deleteBatch(itemIdList) > 0) {
                List<StockNumDTO> stockNumDTOS = BeanUtil.copyList(myShoppingCartItems, StockNumDTO.class);
                int updateStockNumResult = carBMallCarsMapper.updateStockNum(stockNumDTOS);
                if (updateStockNumResult < 1) {
                    CarBMallException.fail(ServiceResultEnum.SHOPPING_ITEM_COUNT_ERROR.getResult());
                }
                String orderNo = NumberUtil.genOrderNo();
                int priceTotal = 0;
                CarBMallOrder carBMallOrder = new CarBMallOrder();
                carBMallOrder.setOrderNo(orderNo);
                carBMallOrder.setUserId(user.getUserId());
                carBMallOrder.setUserAddress(user.getAddress());

                for (CarBMallShoppingCartItemVO carBMallShoppingCartItemVO : myShoppingCartItems) {
                    priceTotal += carBMallShoppingCartItemVO.getCarsCount() * carBMallShoppingCartItemVO.getSellingPrice();
                }
                if (priceTotal < 1) {
                    CarBMallException.fail(ServiceResultEnum.ORDER_PRICE_ERROR.getResult());
                }
                carBMallOrder.setTotalPrice(priceTotal);
                String extraInfo = "";
                carBMallOrder.setExtraInfo(extraInfo);
                if (carBMallOrderMapper.insertSelective(carBMallOrder) > 0) {
                    List<CarBMallOrderItem> carBMallOrderItems = new ArrayList<>();
                    for (CarBMallShoppingCartItemVO carBMallShoppingCartItemVO : myShoppingCartItems) {
                        CarBMallOrderItem carBMallOrderItem = new CarBMallOrderItem();
                        BeanUtil.copyProperties(carBMallShoppingCartItemVO, carBMallOrderItem);
                        carBMallOrderItem.setOrderId(carBMallOrder.getOrderId());
                        carBMallOrderItems.add(carBMallOrderItem);
                    }
                    if (carBMallOrderItemMapper.insertBatch(carBMallOrderItems) > 0) {
                        return orderNo;
                    }
                    CarBMallException.fail(ServiceResultEnum.ORDER_PRICE_ERROR.getResult());
                }
                CarBMallException.fail(ServiceResultEnum.DB_ERROR.getResult());
            }
            CarBMallException.fail(ServiceResultEnum.DB_ERROR.getResult());
        }
        CarBMallException.fail(ServiceResultEnum.SHOPPING_ITEM_ERROR.getResult());
        return ServiceResultEnum.SHOPPING_ITEM_ERROR.getResult();
    }

    @Override
    public CarBMallOrderDetailVO getOrderDetailByOrderNo(String orderNo, Long userId) {
        CarBMallOrder carBMallOrder = carBMallOrderMapper.selectByOrderNo(orderNo);
        if (carBMallOrder == null) {
            CarBMallException.fail(ServiceResultEnum.ORDER_NOT_EXIST_ERROR.getResult());
        }
        if (!userId.equals(carBMallOrder.getUserId())) {
            CarBMallException.fail(ServiceResultEnum.NO_PERMISSION_ERROR.getResult());
        }
        List<CarBMallOrderItem> orderItems = carBMallOrderItemMapper.selectByOrderId(carBMallOrder.getOrderId());
        if (CollectionUtils.isEmpty(orderItems)) {
            CarBMallException.fail(ServiceResultEnum.ORDER_ITEM_NOT_EXIST_ERROR.getResult());
        }
        List<CarBMallOrderItemVO> carBMallOrderItemVOS = BeanUtil.copyList(orderItems, CarBMallOrderItemVO.class);
        CarBMallOrderDetailVO carBMallOrderDetailVO = new CarBMallOrderDetailVO();
        BeanUtil.copyProperties(carBMallOrder, carBMallOrderDetailVO);
        carBMallOrderDetailVO.setOrderStatusString(CarBMallOrderStatusEnum.getCarBMallOrderStatusEnumByStatus(carBMallOrderDetailVO.getOrderStatus()).getName());
        carBMallOrderDetailVO.setPayTypeString(PayTypeEnum.getPayTypeEnumByType(carBMallOrderDetailVO.getPayType()).getName());
        carBMallOrderDetailVO.setCarBMallOrderItemVOS(carBMallOrderItemVOS);
        return carBMallOrderDetailVO;
    }

    @Override
    public CarBMallOrder getCarBMallOrderByOrderNo(String orderNo) {
        return carBMallOrderMapper.selectByOrderNo(orderNo);
    }

    @Override
    public PageResult getMyOrders(PageQueryUtil pageUtil) {
        int total = carBMallOrderMapper.getTotalCarBMallOrders(pageUtil);
        List<CarBMallOrder> carBMallOrders = carBMallOrderMapper.findCarBMallOrderList(pageUtil);
        List<CarBMallOrderListVO> orderListVOS = new ArrayList<>();
        if (total > 0) {
            orderListVOS = BeanUtil.copyList(carBMallOrders, CarBMallOrderListVO.class);
            for (CarBMallOrderListVO carBMallOrderListVO : orderListVOS) {
                carBMallOrderListVO.setOrderStatusString(CarBMallOrderStatusEnum.getCarBMallOrderStatusEnumByStatus(carBMallOrderListVO.getOrderStatus()).getName());
            }
            List<Long> orderIds = carBMallOrders.stream().map(CarBMallOrder::getOrderId).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(orderIds)) {
                List<CarBMallOrderItem> orderItems = carBMallOrderItemMapper.selectByOrderIds(orderIds);
                Map<Long, List<CarBMallOrderItem>> itemByOrderIdMap = orderItems.stream().collect(groupingBy(CarBMallOrderItem::getOrderId));
                for (CarBMallOrderListVO carBMallOrderListVO : orderListVOS) {
                    if (itemByOrderIdMap.containsKey(carBMallOrderListVO.getOrderId())) {
                        List<CarBMallOrderItem> orderItemListTemp = itemByOrderIdMap.get(carBMallOrderListVO.getOrderId());
                        List<CarBMallOrderItemVO> carBMallOrderItemVOS = BeanUtil.copyList(orderItemListTemp, CarBMallOrderItemVO.class);
                        carBMallOrderListVO.setCarBMallOrderItemVOS(carBMallOrderItemVOS);
                    }
                }
            }
        }
        PageResult pageResult = new PageResult(orderListVOS, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    @Transactional
    public String cancelOrder(String orderNo, Long userId) {
        CarBMallOrder carBMallOrder = carBMallOrderMapper.selectByOrderNo(orderNo);
        if (carBMallOrder != null) {
            if (!userId.equals(carBMallOrder.getUserId())) {
                CarBMallException.fail(ServiceResultEnum.NO_PERMISSION_ERROR.getResult());
            }
            if (carBMallOrder.getOrderStatus().intValue() == CarBMallOrderStatusEnum.ORDER_SUCCESS.getOrderStatus()
                    || carBMallOrder.getOrderStatus().intValue() == CarBMallOrderStatusEnum.ORDER_CLOSED_BY_MALLUSER.getOrderStatus()
                    || carBMallOrder.getOrderStatus().intValue() == CarBMallOrderStatusEnum.ORDER_CLOSED_BY_EXPIRED.getOrderStatus()
                    || carBMallOrder.getOrderStatus().intValue() == CarBMallOrderStatusEnum.ORDER_CLOSED_BY_JUDGE.getOrderStatus()) {
                return ServiceResultEnum.ORDER_STATUS_ERROR.getResult();
            }
            if (carBMallOrderMapper.closeOrder(Collections.singletonList(carBMallOrder.getOrderId()), CarBMallOrderStatusEnum.ORDER_CLOSED_BY_MALLUSER.getOrderStatus()) > 0 && recoverStockNum(Collections.singletonList(carBMallOrder.getOrderId()))) {
                return ServiceResultEnum.SUCCESS.getResult();
            } else {
                return ServiceResultEnum.DB_ERROR.getResult();
            }
        }
        return ServiceResultEnum.ORDER_NOT_EXIST_ERROR.getResult();
    }

    @Override
    public String finishOrder(String orderNo, Long userId) {
        CarBMallOrder carBMallOrder = carBMallOrderMapper.selectByOrderNo(orderNo);
        if (carBMallOrder != null) {
            if (!userId.equals(carBMallOrder.getUserId())) {
                return ServiceResultEnum.NO_PERMISSION_ERROR.getResult();
            }
            if (carBMallOrder.getOrderStatus().intValue() != CarBMallOrderStatusEnum.ORDER_EXPRESS.getOrderStatus()) {
                return ServiceResultEnum.ORDER_STATUS_ERROR.getResult();
            }
            carBMallOrder.setOrderStatus((byte) CarBMallOrderStatusEnum.ORDER_SUCCESS.getOrderStatus());
            carBMallOrder.setUpdateTime(new Date());
            if (carBMallOrderMapper.updateByPrimaryKeySelective(carBMallOrder) > 0) {
                return ServiceResultEnum.SUCCESS.getResult();
            } else {
                return ServiceResultEnum.DB_ERROR.getResult();
            }
        }
        return ServiceResultEnum.ORDER_NOT_EXIST_ERROR.getResult();
    }

    @Override
    public String paySuccess(String orderNo, int payType) {
        CarBMallOrder carBMallOrder = carBMallOrderMapper.selectByOrderNo(orderNo);
        if (carBMallOrder != null) {
            if (carBMallOrder.getOrderStatus().intValue() != CarBMallOrderStatusEnum.ORDER_PRE_PAY.getOrderStatus()) {
                return ServiceResultEnum.ORDER_STATUS_ERROR.getResult();
            }
            carBMallOrder.setOrderStatus((byte) CarBMallOrderStatusEnum.ORDER_PAID.getOrderStatus());
            carBMallOrder.setPayType((byte) payType);
            carBMallOrder.setPayStatus((byte) PayStatusEnum.PAY_SUCCESS.getPayStatus());
            carBMallOrder.setPayTime(new Date());
            carBMallOrder.setUpdateTime(new Date());
            if (carBMallOrderMapper.updateByPrimaryKeySelective(carBMallOrder) > 0) {
                return ServiceResultEnum.SUCCESS.getResult();
            } else {
                return ServiceResultEnum.DB_ERROR.getResult();
            }
        }
        return ServiceResultEnum.ORDER_NOT_EXIST_ERROR.getResult();
    }

    @Override
    public List<CarBMallOrderItemVO> getOrderItems(Long id) {
        CarBMallOrder carBMallOrder = carBMallOrderMapper.selectByPrimaryKey(id);
        if (carBMallOrder != null) {
            List<CarBMallOrderItem> orderItems = carBMallOrderItemMapper.selectByOrderId(carBMallOrder.getOrderId());
            if (!CollectionUtils.isEmpty(orderItems)) {
                List<CarBMallOrderItemVO> carBMallOrderItemVOS = BeanUtil.copyList(orderItems, CarBMallOrderItemVO.class);
                return carBMallOrderItemVOS;
            }
        }
        return null;
    }

    /**
     * @param orderIds
     * @return
     */
    public Boolean recoverStockNum(List<Long> orderIds) {
        List<CarBMallOrderItem> carBMallOrderItems = carBMallOrderItemMapper.selectByOrderIds(orderIds);
        List<StockNumDTO> stockNumDTOS = BeanUtil.copyList(carBMallOrderItems, StockNumDTO.class);
        int updateStockNumResult = carBMallCarsMapper.recoverStockNum(stockNumDTOS);
        if (updateStockNumResult < 1) {
            CarBMallException.fail(ServiceResultEnum.CLOSE_ORDER_ERROR.getResult());
            return false;
        } else {
            return true;
        }
    }
}