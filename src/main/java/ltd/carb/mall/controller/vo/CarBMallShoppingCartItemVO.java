 
package ltd.carb.mall.controller.vo;

import java.io.Serializable;

public class CarBMallShoppingCartItemVO implements Serializable {

    private Long cartItemId;

    private Long carsId;

    private Integer carsCount;

    private String carsName;

    private String carsCoverImg;

    private Integer sellingPrice;

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
        this.carsName = carsName;
    }

    public String getCarsCoverImg() {
        return carsCoverImg;
    }

    public void setCarsCoverImg(String carsCoverImg) {
        this.carsCoverImg = carsCoverImg;
    }

    public Integer getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Integer sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Long cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Integer getCarsCount() {
        return carsCount;
    }

    public void setCarsCount(Integer carsCount) {
        this.carsCount = carsCount;
    }
}
