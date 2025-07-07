 
package ltd.carb.mall.entity;

import java.util.Date;

public class CarBMallShoppingCartItem {
    private Long cartItemId;

    private Long userId;

    private Long carsId;

    private Integer carsCount;

    private Byte isDeleted;

    private Date createTime;

    private Date updateTime;

    public Long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Long cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCarsId() {
        return carsId;
    }

    public void setCarsId(Long carsId) {
        this.carsId = carsId;
    }

    public Integer getCarsCount() {
        return carsCount;
    }

    public void setCarsCount(Integer carsCount) {
        this.carsCount = carsCount;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", cartItemId=").append(cartItemId);
        sb.append(", userId=").append(userId);
        sb.append(", carsId=").append(carsId);
        sb.append(", carsCount=").append(carsCount);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}