
package ltd.carb.mall.common;

public enum ServiceResultEnum {
    ERROR("error"),

    SUCCESS("success"),

    DATA_NOT_EXIST("Record not found!"),

    SAME_CATEGORY_EXIST("Category with the same name already exists!"),

    SAME_LOGIN_NAME_EXIST("Username already exists!"),

    LOGIN_NAME_NULL("Please enter your username!"),

    LOGIN_PASSWORD_NULL("Please enter your password!"),

    LOGIN_VERIFY_CODE_NULL("Please enter the verification code!"),

    LOGIN_VERIFY_CODE_ERROR("Verification code is incorrect!"),

    SAME_INDEX_CONFIG_EXIST("The same homepage configuration item already exists!"),

    CARS_CATEGORY_ERROR("Category data error!"),

    SAME_CARS_EXIST("The same car information already exists!"),

    CARS_NOT_EXIST("Car does not exist!"),

    CARS_PUT_DOWN("Car has been taken down!"),

    SHOPPING_CART_ITEM_LIMIT_NUMBER_ERROR("Exceeds the maximum purchase quantity for a single car!"),

    SHOPPING_CART_ITEM_TOTAL_NUMBER_ERROR("Exceeds the maximum capacity of the shopping cart!"),

    LOGIN_ERROR("Login failed!"),

    LOGIN_USER_LOCKED("User has been banned from logging in!"),

    ORDER_NOT_EXIST_ERROR("Order does not exist!"),

    ORDER_ITEM_NOT_EXIST_ERROR("Order item does not exist!"),

    NULL_ADDRESS_ERROR("Address cannot be empty!"),

    ORDER_PRICE_ERROR("Order price error!"),

    ORDER_GENERATE_ERROR("Error generating order!"),

    SHOPPING_ITEM_ERROR("Shopping cart data error!"),

    SHOPPING_ITEM_COUNT_ERROR("Insufficient stock!"),

    ORDER_STATUS_ERROR("Order status error!"),

    CLOSE_ORDER_ERROR("Failed to close the order!"),

    OPERATE_ERROR("Operation failed!"),

    NO_PERMISSION_ERROR("No permission!"),

    DB_ERROR("Database error");

    private String result;

    ServiceResultEnum(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
