 
package ltd.carb.mall.controller.vo;

import java.io.Serializable;

/**
 * Order details page line itemVO
 */
public class CarBMallOrderItemVO implements Serializable {

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

    public Integer getCarsCount() {
        return carsCount;
    }

    public void setCarsCount(Integer carsCount) {
        this.carsCount = carsCount;
    }
}
