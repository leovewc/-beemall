 
package ltd.carb.mall.common;

public class CarBMallException extends RuntimeException {

    public CarBMallException() {
    }

    public CarBMallException(String message) {
        super(message);
    }

    public static void fail(String message) {
        throw new CarBMallException(message);
    }

}
