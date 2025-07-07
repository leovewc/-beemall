 
package ltd.carb.mall.controller.admin;

import ltd.carb.mall.common.CarBMallCategoryLevelEnum;
import ltd.carb.mall.common.CarBMallException;
import ltd.carb.mall.common.ServiceResultEnum;
import ltd.carb.mall.entity.CarsCategory;
import ltd.carb.mall.service.CarBMallCategoryService;
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
import java.util.*;


@Controller
@RequestMapping("/admin")
public class CarBMallCarsCategoryController {

    @Resource
    private CarBMallCategoryService carBMallCategoryService;

    @GetMapping("/categories")
    public String categoriesPage(HttpServletRequest request, @RequestParam("categoryLevel") Byte categoryLevel, @RequestParam("parentId") Long parentId, @RequestParam("backParentId") Long backParentId) {
        if (categoryLevel == null || categoryLevel < 1 || categoryLevel > 3) {
            CarBMallException.fail("Parameter abnormality");
        }
        request.setAttribute("path", "carb_mall_category");
        request.setAttribute("parentId", parentId);
        request.setAttribute("backParentId", backParentId);
        request.setAttribute("categoryLevel", categoryLevel);
        return "admin/carb_mall_category";
    }


    @RequestMapping(value = "/categories/list", method = RequestMethod.GET)
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params) {
        if (ObjectUtils.isEmpty(params.get("page")) || ObjectUtils.isEmpty(params.get("limit")) || ObjectUtils.isEmpty(params.get("categoryLevel")) || ObjectUtils.isEmpty(params.get("parentId"))) {
            return ResultGenerator.genFailResult("Abnormal parameters!");
        }
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(carBMallCategoryService.getCategorisPage(pageUtil));
    }


    @RequestMapping(value = "/categories/listForSelect", method = RequestMethod.GET)
    @ResponseBody
    public Result listForSelect(@RequestParam("categoryId") Long categoryId) {
        if (categoryId == null || categoryId < 1) {
            return ResultGenerator.genFailResult("Missing parameters!");
        }
        CarsCategory category = carBMallCategoryService.getCarsCategoryById(categoryId);

        if (category == null || category.getCategoryLevel() == CarBMallCategoryLevelEnum.LEVEL_THREE.getLevel()) {
            return ResultGenerator.genFailResult("Abnormal parameters!");
        }
        Map categoryResult = new HashMap(4);
        if (category.getCategoryLevel() == CarBMallCategoryLevelEnum.LEVEL_ONE.getLevel()) {

            List<CarsCategory> secondLevelCategories = carBMallCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(categoryId), CarBMallCategoryLevelEnum.LEVEL_TWO.getLevel());
            if (!CollectionUtils.isEmpty(secondLevelCategories)) {

                List<CarsCategory> thirdLevelCategories = carBMallCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(secondLevelCategories.get(0).getCategoryId()), CarBMallCategoryLevelEnum.LEVEL_THREE.getLevel());
                categoryResult.put("secondLevelCategories", secondLevelCategories);
                categoryResult.put("thirdLevelCategories", thirdLevelCategories);
            }
        }
        if (category.getCategoryLevel() == CarBMallCategoryLevelEnum.LEVEL_TWO.getLevel()) {

            List<CarsCategory> thirdLevelCategories = carBMallCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(categoryId), CarBMallCategoryLevelEnum.LEVEL_THREE.getLevel());
            categoryResult.put("thirdLevelCategories", thirdLevelCategories);
        }
        return ResultGenerator.genSuccessResult(categoryResult);
    }

    @RequestMapping(value = "/categories/save", method = RequestMethod.POST)
    @ResponseBody
    public Result save(@RequestBody CarsCategory carsCategory) {
        if (Objects.isNull(carsCategory.getCategoryLevel())
                || !StringUtils.hasText(carsCategory.getCategoryName())
                || Objects.isNull(carsCategory.getParentId())
                || Objects.isNull(carsCategory.getCategoryRank())) {
            return ResultGenerator.genFailResult("Abnormal parameters!");
        }
        String result = carBMallCategoryService.saveCategory(carsCategory);
        if (ServiceResultEnum.SUCCESS.getResult().equals(result)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(result);
        }
    }



    @RequestMapping(value = "/categories/update", method = RequestMethod.POST)
    @ResponseBody
    public Result update(@RequestBody CarsCategory carsCategory) {
        if (Objects.isNull(carsCategory.getCategoryId())
                || Objects.isNull(carsCategory.getCategoryLevel())
                || !StringUtils.hasText(carsCategory.getCategoryName())
                || Objects.isNull(carsCategory.getParentId())
                || Objects.isNull(carsCategory.getCategoryRank())) {
            return ResultGenerator.genFailResult("Abnormal parameters!");
        }
        String result = carBMallCategoryService.updateCarsCategory(carsCategory);
        if (ServiceResultEnum.SUCCESS.getResult().equals(result)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(result);
        }
    }

    @GetMapping("/categories/info/{id}")
    @ResponseBody
    public Result info(@PathVariable("id") Long id) {
        CarsCategory carsCategory = carBMallCategoryService.getCarsCategoryById(id);
        if (carsCategory == null) {
            return ResultGenerator.genFailResult("No data found");
        }
        return ResultGenerator.genSuccessResult(carsCategory);
    }


    @RequestMapping(value = "/categories/delete", method = RequestMethod.POST)
    @ResponseBody
    public Result delete(@RequestBody Integer[] ids) {
        if (ids.length < 1) {
            return ResultGenerator.genFailResult("Abnormal parameters!");
        }
        if (carBMallCategoryService.deleteBatch(ids)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("failed to delete");
        }
    }


}