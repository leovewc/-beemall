 
package ltd.carb.mall.controller.mall;

import ltd.carb.mall.common.Constants;
import ltd.carb.mall.common.CarBMallException;
import ltd.carb.mall.common.ServiceResultEnum;
import ltd.carb.mall.controller.vo.CarBMallCarsDetailVO;
import ltd.carb.mall.controller.vo.SearchPageCategoryVO;
import ltd.carb.mall.entity.CarBMallCars;
import ltd.carb.mall.service.CarBMallCategoryService;
import ltd.carb.mall.service.CarBMallCarsService;
import ltd.carb.mall.util.BeanUtil;
import ltd.carb.mall.util.PageQueryUtil;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class CarsController {

    @Resource
    private CarBMallCarsService carBMallCarsService;
    @Resource
    private CarBMallCategoryService carBMallCategoryService;

    @GetMapping({"/search", "/search.html"})
    public String searchPage(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        if (ObjectUtils.isEmpty(params.get("page"))) {
            params.put("page", 1);
        }
        params.put("limit", Constants.CARS_SEARCH_PAGE_LIMIT);
        if (params.containsKey("carsCategoryId") && StringUtils.hasText(params.get("carsCategoryId") + "")) {
            Long categoryId = Long.valueOf(params.get("carsCategoryId") + "");
            SearchPageCategoryVO searchPageCategoryVO = carBMallCategoryService.getCategoriesForSearch(categoryId);
            if (searchPageCategoryVO != null) {
                request.setAttribute("carsCategoryId", categoryId);
                request.setAttribute("searchPageCategoryVO", searchPageCategoryVO);
            }
        }
        if (params.containsKey("orderBy") && StringUtils.hasText(params.get("orderBy") + "")) {
            request.setAttribute("orderBy", params.get("orderBy") + "");
        }
        String keyword = "";
        if (params.containsKey("keyword") && StringUtils.hasText((params.get("keyword") + "").trim())) {
            keyword = params.get("keyword") + "";
        }
        request.setAttribute("keyword", keyword);
        params.put("keyword", keyword);
        params.put("carsSellStatus", Constants.SELL_STATUS_UP);
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        request.setAttribute("pageResult", carBMallCarsService.searchCarBMallCars(pageUtil));
        return "mall/search";
    }

    @GetMapping("/cars/detail/{carsId}")
    public String detailPage(@PathVariable("carsId") Long carsId, HttpServletRequest request) {
        if (carsId < 1) {
            CarBMallException.fail("Parameter abnormality");
        }
        CarBMallCars cars = carBMallCarsService.getCarBMallCarsById(carsId);
        if (Constants.SELL_STATUS_UP != cars.getCarsSellStatus()) {
            CarBMallException.fail(ServiceResultEnum.CARS_PUT_DOWN.getResult());
        }
        CarBMallCarsDetailVO carsDetailVO = new CarBMallCarsDetailVO();
        BeanUtil.copyProperties(cars, carsDetailVO);
        carsDetailVO.setCarsCarouselList(cars.getCarsCarousel().split(","));
        request.setAttribute("carsDetail", carsDetailVO);
        return "mall/detail";
    }

}
