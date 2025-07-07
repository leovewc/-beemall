 
package ltd.carb.mall.service;

import ltd.carb.mall.controller.vo.CarBMallIndexConfigCarsVO;
import ltd.carb.mall.entity.IndexConfig;
import ltd.carb.mall.util.PageQueryUtil;
import ltd.carb.mall.util.PageResult;

import java.util.List;

public interface CarBMallIndexConfigService {
    /**
     *
     * @param pageUtil
     * @return
     */
    PageResult getConfigsPage(PageQueryUtil pageUtil);

    String saveIndexConfig(IndexConfig indexConfig);

    String updateIndexConfig(IndexConfig indexConfig);

    IndexConfig getIndexConfigById(Long id);

    /**
     *
     * @param number
     * @return
     */
    List<CarBMallIndexConfigCarsVO> getConfigCarsForIndex(int configType, int number);

    Boolean deleteBatch(Long[] ids);
}
