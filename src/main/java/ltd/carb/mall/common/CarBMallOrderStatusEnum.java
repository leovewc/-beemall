 
package ltd.carb.mall.common;


public enum CarBMallOrderStatusEnum {

    DEFAULT(-9, "ERROR"),
    ORDER_PRE_PAY(0, "order pre pay"),
    ORDER_PAID(1, "order paid"),
    ORDER_PACKAGED(2, "order packaged"),
    ORDER_EXPRESS(3, "order express"),
    ORDER_SUCCESS(4, "order success"),
    ORDER_CLOSED_BY_MALLUSER(-1, "order closed by mall user"),
    ORDER_CLOSED_BY_EXPIRED(-2, "order closed by expired"),
    ORDER_CLOSED_BY_JUDGE(-3, "order closed by judge"),;

    private int orderStatus;

    private String name;

    CarBMallOrderStatusEnum(int orderStatus, String name) {
        this.orderStatus = orderStatus;
        this.name = name;
    }

    public static CarBMallOrderStatusEnum getCarBMallOrderStatusEnumByStatus(int orderStatus) {
        for (CarBMallOrderStatusEnum carBMallOrderStatusEnum : CarBMallOrderStatusEnum.values()) {
            if (carBMallOrderStatusEnum.getOrderStatus() == orderStatus) {
                return carBMallOrderStatusEnum;
            }
        }
        return DEFAULT;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
