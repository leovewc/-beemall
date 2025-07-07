 
package ltd.carb.mall.common;


public enum CarBMallCategoryLevelEnum {

    DEFAULT(0, "ERROR"),
    LEVEL_ONE(1, "level one"),
    LEVEL_TWO(2, "level two"),
    LEVEL_THREE(3, "level three"),;

    private int level;

    private String name;

    CarBMallCategoryLevelEnum(int level, String name) {
        this.level = level;
        this.name = name;
    }

    public static CarBMallCategoryLevelEnum getCarBMallOrderStatusEnumByLevel(int level) {
        for (CarBMallCategoryLevelEnum carBMallCategoryLevelEnum : CarBMallCategoryLevelEnum.values()) {
            if (carBMallCategoryLevelEnum.getLevel() == level) {
                return carBMallCategoryLevelEnum;
            }
        }
        return DEFAULT;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
