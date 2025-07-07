 
package ltd.carb.mall.controller.admin;

import ltd.carb.mall.common.Constants;
import ltd.carb.mall.common.CarBMallCategoryLevelEnum;
import ltd.carb.mall.common.CarBMallException;
import ltd.carb.mall.common.ServiceResultEnum;
import ltd.carb.mall.entity.CarsCategory;
import ltd.carb.mall.entity.CarBMallCars;
import ltd.carb.mall.service.CarBMallCategoryService;
import ltd.carb.mall.service.CarBMallCarsService;
import ltd.carb.mall.util.PageQueryUtil;
import ltd.carb.mall.util.Result;
import ltd.carb.mall.util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@Controller
@RequestMapping("/admin")
public class CarBMallCarsController {

    @Resource
    private CarBMallCarsService carBMallCarsService;
    @Resource
    private CarBMallCategoryService carBMallCategoryService;

    @GetMapping("/cars")
    public String carsPage(HttpServletRequest request) {
        request.setAttribute("path", "carb_mall_cars");
        return "admin/carb_mall_cars";
    }

    @GetMapping("/cars/edit")
    public String edit(HttpServletRequest request) {
        request.setAttribute("path", "edit");
        //1
        List<CarsCategory> firstLevelCategories = carBMallCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(0L), CarBMallCategoryLevelEnum.LEVEL_ONE.getLevel());
        if (!CollectionUtils.isEmpty(firstLevelCategories)) {
            //2
            List<CarsCategory> secondLevelCategories = carBMallCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(firstLevelCategories.get(0).getCategoryId()), CarBMallCategoryLevelEnum.LEVEL_TWO.getLevel());
            if (!CollectionUtils.isEmpty(secondLevelCategories)) {
                //3
                List<CarsCategory> thirdLevelCategories = carBMallCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(secondLevelCategories.get(0).getCategoryId()), CarBMallCategoryLevelEnum.LEVEL_THREE.getLevel());
                request.setAttribute("firstLevelCategories", firstLevelCategories);
                request.setAttribute("secondLevelCategories", secondLevelCategories);
                request.setAttribute("thirdLevelCategories", thirdLevelCategories);
                request.setAttribute("path", "cars-edit");
                return "admin/carb_mall_cars_edit";
            }
        }
        CarBMallException.fail("Imperfect classification data");
        return null;
    }

    @GetMapping("/cars/edit/{carsId}")
    public String edit(HttpServletRequest request, @PathVariable("carsId") Long carsId) {
        request.setAttribute("path", "edit");
        CarBMallCars carBMallCars = carBMallCarsService.getCarBMallCarsById(carsId);
        if (carBMallCars.getCarsCategoryId() > 0) {
            if (carBMallCars.getCarsCategoryId() != null || carBMallCars.getCarsCategoryId() > 0) {
                //Three-level linkage
                CarsCategory currentCarsCategory = carBMallCategoryService.getCarsCategoryById(carBMallCars.getCarsCategoryId());
                if (currentCarsCategory != null && currentCarsCategory.getCategoryLevel() == CarBMallCategoryLevelEnum.LEVEL_THREE.getLevel()) {
                    List<CarsCategory> firstLevelCategories = carBMallCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(0L), CarBMallCategoryLevelEnum.LEVEL_ONE.getLevel());
                    List<CarsCategory> thirdLevelCategories = carBMallCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(currentCarsCategory.getParentId()), CarBMallCategoryLevelEnum.LEVEL_THREE.getLevel());
                    CarsCategory secondCategory = carBMallCategoryService.getCarsCategoryById(currentCarsCategory.getParentId());
                    if (secondCategory != null) {
                        List<CarsCategory> secondLevelCategories = carBMallCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(secondCategory.getParentId()), CarBMallCategoryLevelEnum.LEVEL_TWO.getLevel());
                        CarsCategory firestCategory = carBMallCategoryService.getCarsCategoryById(secondCategory.getParentId());
                        if (firestCategory != null) {
                            request.setAttribute("firstLevelCategories", firstLevelCategories);
                            request.setAttribute("secondLevelCategories", secondLevelCategories);
                            request.setAttribute("thirdLevelCategories", thirdLevelCategories);
                            request.setAttribute("firstLevelCategoryId", firestCategory.getCategoryId());
                            request.setAttribute("secondLevelCategoryId", secondCategory.getCategoryId());
                            request.setAttribute("thirdLevelCategoryId", currentCarsCategory.getCategoryId());
                        }
                    }
                }
            }
        }
        if (carBMallCars.getCarsCategoryId() == 0) {
            //1
            List<CarsCategory> firstLevelCategories = carBMallCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(0L), CarBMallCategoryLevelEnum.LEVEL_ONE.getLevel());
            if (!CollectionUtils.isEmpty(firstLevelCategories)) {
                //2
                List<CarsCategory> secondLevelCategories = carBMallCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(firstLevelCategories.get(0).getCategoryId()), CarBMallCategoryLevelEnum.LEVEL_TWO.getLevel());
                if (!CollectionUtils.isEmpty(secondLevelCategories)) {
                    //3
                    List<CarsCategory> thirdLevelCategories = carBMallCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(secondLevelCategories.get(0).getCategoryId()), CarBMallCategoryLevelEnum.LEVEL_THREE.getLevel());
                    request.setAttribute("firstLevelCategories", firstLevelCategories);
                    request.setAttribute("secondLevelCategories", secondLevelCategories);
                    request.setAttribute("thirdLevelCategories", thirdLevelCategories);
                }
            }
        }
        request.setAttribute("cars", carBMallCars);
        request.setAttribute("path", "cars-edit");
        return "admin/carb_mall_cars_edit";
    }

    /**
     * list
     */
    @RequestMapping(value = "/cars/list", method = RequestMethod.GET)
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params) {
        if (ObjectUtils.isEmpty(params.get("page")) || ObjectUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genFailResult("Abnormal parameters!");
        }
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(carBMallCarsService.getCarBMallCarsPage(pageUtil));
    }

    /**
     * add
     */
    @RequestMapping(value = "/cars/save", method = RequestMethod.POST)
    @ResponseBody
    public Result save(@RequestBody CarBMallCars carBMallCars) {
        if (!StringUtils.hasText(carBMallCars.getCarsName())
                || !StringUtils.hasText(carBMallCars.getCarsIntro())
                || !StringUtils.hasText(carBMallCars.getTag())
                || Objects.isNull(carBMallCars.getOriginalPrice())
                || Objects.isNull(carBMallCars.getCarsCategoryId())
                || Objects.isNull(carBMallCars.getSellingPrice())
                || Objects.isNull(carBMallCars.getStockNum())
                || Objects.isNull(carBMallCars.getCarsSellStatus())
                || !StringUtils.hasText(carBMallCars.getCarsCoverImg())
                || !StringUtils.hasText(carBMallCars.getCarsDetailContent())) {
            return ResultGenerator.genFailResult("Abnormal parameters!");
        }
        String result = carBMallCarsService.saveCarBMallCars(carBMallCars);
        if (ServiceResultEnum.SUCCESS.getResult().equals(result)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(result);
        }
    }


    /**
     * update
     */
    @RequestMapping(value = "/cars/update", method = RequestMethod.POST)
    @ResponseBody
    public Result update(@RequestBody CarBMallCars carBMallCars) {
        if (Objects.isNull(carBMallCars.getCarsId())
                || !StringUtils.hasText(carBMallCars.getCarsName())
                || !StringUtils.hasText(carBMallCars.getCarsIntro())
                || !StringUtils.hasText(carBMallCars.getTag())
                || Objects.isNull(carBMallCars.getOriginalPrice())
                || Objects.isNull(carBMallCars.getSellingPrice())
                || Objects.isNull(carBMallCars.getCarsCategoryId())
                || Objects.isNull(carBMallCars.getStockNum())
                || Objects.isNull(carBMallCars.getCarsSellStatus())
                || !StringUtils.hasText(carBMallCars.getCarsCoverImg())
                || !StringUtils.hasText(carBMallCars.getCarsDetailContent())) {
            return ResultGenerator.genFailResult("Abnormal parameters!");
        }
        String result = carBMallCarsService.updateCarBMallCars(carBMallCars);
        if (ServiceResultEnum.SUCCESS.getResult().equals(result)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(result);
        }
    }


    @GetMapping("/cars/info/{id}")
    @ResponseBody
    public Result info(@PathVariable("id") Long id) {
        CarBMallCars cars = carBMallCarsService.getCarBMallCarsById(id);
        return ResultGenerator.genSuccessResult(cars);
    }


    @RequestMapping(value = "/cars/status/{sellStatus}", method = RequestMethod.PUT)
    @ResponseBody
    public Result delete(@RequestBody Long[] ids, @PathVariable("sellStatus") int sellStatus) {
        if (ids.length < 1) {
            return ResultGenerator.genFailResult("Abnormal parameters!");
        }
        if (sellStatus != Constants.SELL_STATUS_UP && sellStatus != Constants.SELL_STATUS_DOWN) {
            return ResultGenerator.genFailResult("Abnormal status!");
        }
        if (carBMallCarsService.batchUpdateSellStatus(ids, sellStatus)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("fail to edit");
        }
    }

}