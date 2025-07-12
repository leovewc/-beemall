 
package ltd.carb.mall.controller.mall;

import ltd.carb.mall.common.Constants;
import ltd.carb.mall.common.IndexConfigTypeEnum;
import ltd.carb.mall.common.CarBMallException;
import ltd.carb.mall.controller.vo.CarBMallIndexCarouselVO;
import ltd.carb.mall.controller.vo.CarBMallIndexCategoryVO;
import ltd.carb.mall.controller.vo.CarBMallIndexConfigCarsVO;
import ltd.carb.mall.service.CarBMallCarouselService;
import ltd.carb.mall.service.CarBMallCategoryService;
import ltd.carb.mall.service.CarBMallIndexConfigService;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Resource
    private CarBMallCarouselService carBMallCarouselService;

    @Resource
    private CarBMallIndexConfigService carBMallIndexConfigService;

    @Resource
    private CarBMallCategoryService carBMallCategoryService;

    @GetMapping({"/index", "/", "/index.html"})
    public String indexPage(HttpServletRequest request) {
        List<CarBMallIndexCategoryVO> categories = carBMallCategoryService.getCategoriesForIndex();
        if (CollectionUtils.isEmpty(categories)) {
            CarBMallException.fail("Imperfect classification data");
        }
        List<CarBMallIndexCarouselVO> carousels = carBMallCarouselService.getCarouselsForIndex(Constants.INDEX_CAROUSEL_NUMBER);
        List<CarBMallIndexConfigCarsVO> hotCars = carBMallIndexConfigService.getConfigCarsForIndex(IndexConfigTypeEnum.INDEX_CARS_HOT.getType(), Constants.INDEX_CARS_HOT_NUMBER);
        List<CarBMallIndexConfigCarsVO> newCars = carBMallIndexConfigService.getConfigCarsForIndex(IndexConfigTypeEnum.INDEX_CARS_NEW.getType(), Constants.INDEX_CARS_NEW_NUMBER);
        List<CarBMallIndexConfigCarsVO> recommendCars = carBMallIndexConfigService.getConfigCarsForIndex(IndexConfigTypeEnum.INDEX_CARS_RECOMMOND.getType(), Constants.INDEX_CARS_RECOMMOND_NUMBER);
        request.setAttribute("categories", categories);
        request.setAttribute("carousels", carousels);
        request.setAttribute("hotCars", hotCars);
        request.setAttribute("newCars", newCars);
        request.setAttribute("recommendCars", recommendCars);
        return "mall/index";
    }

    @GetMapping("/location")
    public String locationPage() {
        return "mall/location";
    }
}
