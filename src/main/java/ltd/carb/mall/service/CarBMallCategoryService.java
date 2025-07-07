 
package ltd.carb.mall.service;

import ltd.carb.mall.controller.vo.CarBMallIndexCategoryVO;
import ltd.carb.mall.controller.vo.SearchPageCategoryVO;
import ltd.carb.mall.entity.CarsCategory;
import ltd.carb.mall.util.PageQueryUtil;
import ltd.carb.mall.util.PageResult;

import java.util.List;

public interface CarBMallCategoryService {
    /**
     *
     * @param pageUtil
     * @return
     */
    PageResult getCategorisPage(PageQueryUtil pageUtil);

    String saveCategory(CarsCategory carsCategory);

    String updateCarsCategory(CarsCategory carsCategory);

    CarsCategory getCarsCategoryById(Long id);

    Boolean deleteBatch(Integer[] ids);

    /**
     *
     * @return
     */
    List<CarBMallIndexCategoryVO> getCategoriesForIndex();

    /**
     *
     * @param categoryId
     * @return
     */
    SearchPageCategoryVO getCategoriesForSearch(Long categoryId);

    /**
     *
     * @param parentIds
     * @param categoryLevel
     * @return
     */
    List<CarsCategory> selectByLevelAndParentIdsAndNumber(List<Long> parentIds, int categoryLevel);
}
