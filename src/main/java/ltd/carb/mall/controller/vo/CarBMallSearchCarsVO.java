 
package ltd.carb.mall.controller.vo;

import java.io.Serializable;

/**
 * SearchVO
 */
public class CarBMallSearchCarsVO implements Serializable {

    private Long carsId;

    private String carsName;

    private String carsIntro;

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

    public String getCarsIntro() {
        return carsIntro;
    }

    public void setCarsIntro(String carsIntro) {
        this.carsIntro = carsIntro;
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
}
