 
package ltd.carb.mall.controller.vo;

import java.io.Serializable;

public class CarBMallCarsDetailVO implements Serializable {

    private Long carsId;

    private String carsName;

    private String carsIntro;

    private String carsCoverImg;

    private String[] carsCarouselList;

    private Integer sellingPrice;

    private Integer originalPrice;
    
    private Integer stockNum;

    private String carsDetailContent;
    
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

    public Integer getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Integer originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Integer getStockNum() {
        return stockNum;
    }

    public void setStockNum(Integer stockNum) {
        this.stockNum = stockNum;
    }

    public String getCarsDetailContent() {
        return carsDetailContent;
    }

    public void setCarsDetailContent(String carsDetailContent) {
        this.carsDetailContent = carsDetailContent;
    }

    public String[] getCarsCarouselList() {
        return carsCarouselList;
    }

    public void setCarsCarouselList(String[] carsCarouselList) {
        this.carsCarouselList = carsCarouselList;
    }
}