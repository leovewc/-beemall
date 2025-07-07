 
package ltd.carb.mall.dao;

import ltd.carb.mall.entity.CarBMallCars;
import ltd.carb.mall.entity.StockNumDTO;
import ltd.carb.mall.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CarBMallCarsMapper {
    int deleteByPrimaryKey(Long carsId);

    int insert(CarBMallCars record);

    int insertSelective(CarBMallCars record);

    CarBMallCars selectByPrimaryKey(Long carsId);

    CarBMallCars selectByCategoryIdAndName(@Param("carsName") String carsName, @Param("carsCategoryId") Long carsCategoryId);

    int updateByPrimaryKeySelective(CarBMallCars record);

    int updateByPrimaryKeyWithBLOBs(CarBMallCars record);

    int updateByPrimaryKey(CarBMallCars record);

    List<CarBMallCars> findCarBMallCarsList(PageQueryUtil pageUtil);

    int getTotalCarBMallCars(PageQueryUtil pageUtil);

    List<CarBMallCars> selectByPrimaryKeys(List<Long> carsIds);

    List<CarBMallCars> findCarBMallCarsListBySearch(PageQueryUtil pageUtil);

    int getTotalCarBMallCarsBySearch(PageQueryUtil pageUtil);

    int batchInsert(@Param("CarBMallCarsList") List<CarBMallCars> carBMallCarsList);

    int updateStockNum(@Param("stockNumDTOS") List<StockNumDTO> stockNumDTOS);

    int recoverStockNum(@Param("stockNumDTOS") List<StockNumDTO> stockNumDTOS);

    int batchUpdateSellStatus(@Param("orderIds")Long[] orderIds,@Param("sellStatus") int sellStatus);

}