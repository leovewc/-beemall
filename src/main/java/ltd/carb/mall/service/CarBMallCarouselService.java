 
package ltd.carb.mall.service;

import ltd.carb.mall.controller.vo.CarBMallIndexCarouselVO;
import ltd.carb.mall.entity.Carousel;
import ltd.carb.mall.util.PageQueryUtil;
import ltd.carb.mall.util.PageResult;

import java.util.List;

public interface CarBMallCarouselService {
    /**
     *
     * @param pageUtil
     * @return
     */
    PageResult getCarouselPage(PageQueryUtil pageUtil);

    String saveCarousel(Carousel carousel);

    String updateCarousel(Carousel carousel);

    Carousel getCarouselById(Integer id);

    Boolean deleteBatch(Integer[] ids);

    /**
     *
     * @param number
     * @return
     */
    List<CarBMallIndexCarouselVO> getCarouselsForIndex(int number);
}
