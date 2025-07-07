 
package ltd.carb.mall.service;

import ltd.carb.mall.entity.CarBMallCars;
import ltd.carb.mall.util.PageQueryUtil;
import ltd.carb.mall.util.PageResult;

import java.util.List;

public interface CarBMallCarsService {
    /**
     *
     * @param pageUtil
     * @return
     */
    PageResult getCarBMallCarsPage(PageQueryUtil pageUtil);

    /**
     *
     * @param cars
     * @return
     */
    String saveCarBMallCars(CarBMallCars cars);

    /**
     *
     * @param carBMallCarsList
     * @return
     */
    void batchSaveCarBMallCars(List<CarBMallCars> carBMallCarsList);

    /**
     *
     * @param cars
     * @return
     */
    String updateCarBMallCars(CarBMallCars cars);

    /**
     *
     * @param id
     * @return
     */
    CarBMallCars getCarBMallCarsById(Long id);

    /**
     *
     * @param ids
     * @return
     */
    Boolean batchUpdateSellStatus(Long[] ids,int sellStatus);

    /**
     *
     * @param pageUtil
     * @return
     */
    PageResult searchCarBMallCars(PageQueryUtil pageUtil);
}
