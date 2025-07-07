 
package ltd.carb.mall.entity;

import java.util.Date;

public class CarBMallOrderItem {
    private Long orderItemId;

    private Long orderId;

    private Long carsId;

    private String carsName;

    private String carsCoverImg;

    private Integer sellingPrice;

    private Integer carsCount;

    private Date createTime;

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCarsId() {
        return carsId;
    }

    public void setCarsId(Long carsId) {
        this.carsId = carsId;
    }

    public String getCarsName() {
        return carsName;
    }

    public void setCarsName(String carsName) {
        this.carsName = carsName == null ? null : carsName.trim();
    }

    public String getCarsCoverImg() {
        return carsCoverImg;
    }

    public void setCarsCoverImg(String carsCoverImg) {
        this.carsCoverImg = carsCoverImg == null ? null : carsCoverImg.trim();
    }

    public Integer getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Integer sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Integer getCarsCount() {
        return carsCount;
    }

    public void setCarsCount(Integer carsCount) {
        this.carsCount = carsCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", orderItemId=").append(orderItemId);
        sb.append(", orderId=").append(orderId);
        sb.append(", carsId=").append(carsId);
        sb.append(", carsName=").append(carsName);
        sb.append(", carsCoverImg=").append(carsCoverImg);
        sb.append(", sellingPrice=").append(sellingPrice);
        sb.append(", carsCount=").append(carsCount);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}