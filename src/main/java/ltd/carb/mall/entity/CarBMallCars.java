 
package ltd.carb.mall.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class CarBMallCars {
    private Long carsId;

    private String carsName;

    private String carsIntro;

    private Long carsCategoryId;

    private String carsCoverImg;

    private String carsCarousel;

    private Integer originalPrice;

    private Integer sellingPrice;

    private Integer stockNum;

    private String tag;

    private Byte carsSellStatus;

    private Integer createUser;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private Integer updateUser;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

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
        this.carsName = carsName == null ? null : carsName.trim();
    }

    public String getCarsIntro() {
        return carsIntro;
    }

    public void setCarsIntro(String carsIntro) {
        this.carsIntro = carsIntro == null ? null : carsIntro.trim();
    }

    public Long getCarsCategoryId() {
        return carsCategoryId;
    }

    public void setCarsCategoryId(Long carsCategoryId) {
        this.carsCategoryId = carsCategoryId;
    }

    public String getCarsCoverImg() {
        return carsCoverImg;
    }

    public void setCarsCoverImg(String carsCoverImg) {
        this.carsCoverImg = carsCoverImg == null ? null : carsCoverImg.trim();
    }

    public String getCarsCarousel() {
        return carsCarousel;
    }

    public void setCarsCarousel(String carsCarousel) {
        this.carsCarousel = carsCarousel == null ? null : carsCarousel.trim();
    }

    public Integer getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Integer originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Integer getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Integer sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Integer getStockNum() {
        return stockNum;
    }

    public void setStockNum(Integer stockNum) {
        this.stockNum = stockNum;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    public Byte getCarsSellStatus() {
        return carsSellStatus;
    }

    public void setCarsSellStatus(Byte carsSellStatus) {
        this.carsSellStatus = carsSellStatus;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCarsDetailContent() {
        return carsDetailContent;
    }

    public void setCarsDetailContent(String carsDetailContent) {
        this.carsDetailContent = carsDetailContent == null ? null : carsDetailContent.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", carsId=").append(carsId);
        sb.append(", carsName=").append(carsName);
        sb.append(", carsIntro=").append(carsIntro);
        sb.append(", carsCoverImg=").append(carsCoverImg);
        sb.append(", carsCarousel=").append(carsCarousel);
        sb.append(", originalPrice=").append(originalPrice);
        sb.append(", sellingPrice=").append(sellingPrice);
        sb.append(", stockNum=").append(stockNum);
        sb.append(", tag=").append(tag);
        sb.append(", carsSellStatus=").append(carsSellStatus);
        sb.append(", createUser=").append(createUser);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", carsDetailContent=").append(carsDetailContent);
        sb.append("]");
        return sb.toString();
    }
}