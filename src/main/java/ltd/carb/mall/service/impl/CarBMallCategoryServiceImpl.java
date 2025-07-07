 
package ltd.carb.mall.service.impl;

import ltd.carb.mall.common.Constants;
import ltd.carb.mall.common.CarBMallCategoryLevelEnum;
import ltd.carb.mall.common.ServiceResultEnum;
import ltd.carb.mall.controller.vo.CarBMallIndexCategoryVO;
import ltd.carb.mall.controller.vo.SearchPageCategoryVO;
import ltd.carb.mall.controller.vo.SecondLevelCategoryVO;
import ltd.carb.mall.controller.vo.ThirdLevelCategoryVO;
import ltd.carb.mall.dao.CarsCategoryMapper;
import ltd.carb.mall.entity.CarsCategory;
import ltd.carb.mall.service.CarBMallCategoryService;
import ltd.carb.mall.util.BeanUtil;
import ltd.carb.mall.util.PageQueryUtil;
import ltd.carb.mall.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
public class CarBMallCategoryServiceImpl implements CarBMallCategoryService {

    @Autowired
    private CarsCategoryMapper carsCategoryMapper;

    @Override
    public PageResult getCategorisPage(PageQueryUtil pageUtil) {
        List<CarsCategory> carsCategories = carsCategoryMapper.findCarsCategoryList(pageUtil);
        int total = carsCategoryMapper.getTotalCarsCategories(pageUtil);
        PageResult pageResult = new PageResult(carsCategories, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public String saveCategory(CarsCategory carsCategory) {
        CarsCategory temp = carsCategoryMapper.selectByLevelAndName(carsCategory.getCategoryLevel(), carsCategory.getCategoryName());
        if (temp != null) {
            return ServiceResultEnum.SAME_CATEGORY_EXIST.getResult();
        }
        if (carsCategoryMapper.insertSelective(carsCategory) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public String updateCarsCategory(CarsCategory carsCategory) {
        CarsCategory temp = carsCategoryMapper.selectByPrimaryKey(carsCategory.getCategoryId());
        if (temp == null) {
            return ServiceResultEnum.DATA_NOT_EXIST.getResult();
        }
        CarsCategory temp2 = carsCategoryMapper.selectByLevelAndName(carsCategory.getCategoryLevel(), carsCategory.getCategoryName());
        if (temp2 != null && !temp2.getCategoryId().equals(carsCategory.getCategoryId())) {
            return ServiceResultEnum.SAME_CATEGORY_EXIST.getResult();
        }
        carsCategory.setUpdateTime(new Date());
        if (carsCategoryMapper.updateByPrimaryKeySelective(carsCategory) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public CarsCategory getCarsCategoryById(Long id) {
        return carsCategoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean deleteBatch(Integer[] ids) {
        if (ids.length < 1) {
            return false;
        }
        return carsCategoryMapper.deleteBatch(ids) > 0;
    }

    @Override
    public List<CarBMallIndexCategoryVO> getCategoriesForIndex() {
        List<CarBMallIndexCategoryVO> carBMallIndexCategoryVOS = new ArrayList<>();
        //1
        List<CarsCategory> firstLevelCategories = carsCategoryMapper.selectByLevelAndParentIdsAndNumber(Collections.singletonList(0L), CarBMallCategoryLevelEnum.LEVEL_ONE.getLevel(), Constants.INDEX_CATEGORY_NUMBER);
        if (!CollectionUtils.isEmpty(firstLevelCategories)) {
            List<Long> firstLevelCategoryIds = firstLevelCategories.stream().map(CarsCategory::getCategoryId).collect(Collectors.toList());
            //2
            List<CarsCategory> secondLevelCategories = carsCategoryMapper.selectByLevelAndParentIdsAndNumber(firstLevelCategoryIds, CarBMallCategoryLevelEnum.LEVEL_TWO.getLevel(), 0);
            if (!CollectionUtils.isEmpty(secondLevelCategories)) {
                List<Long> secondLevelCategoryIds = secondLevelCategories.stream().map(CarsCategory::getCategoryId).collect(Collectors.toList());
                //3
                List<CarsCategory> thirdLevelCategories = carsCategoryMapper.selectByLevelAndParentIdsAndNumber(secondLevelCategoryIds, CarBMallCategoryLevelEnum.LEVEL_THREE.getLevel(), 0);
                if (!CollectionUtils.isEmpty(thirdLevelCategories)) {
                    Map<Long, List<CarsCategory>> thirdLevelCategoryMap = thirdLevelCategories.stream().collect(groupingBy(CarsCategory::getParentId));
                    List<SecondLevelCategoryVO> secondLevelCategoryVOS = new ArrayList<>();
                    //2
                    for (CarsCategory secondLevelCategory : secondLevelCategories) {
                        SecondLevelCategoryVO secondLevelCategoryVO = new SecondLevelCategoryVO();
                        BeanUtil.copyProperties(secondLevelCategory, secondLevelCategoryVO);
                        if (thirdLevelCategoryMap.containsKey(secondLevelCategory.getCategoryId())) {
                            //2
                            List<CarsCategory> tempCarsCategories = thirdLevelCategoryMap.get(secondLevelCategory.getCategoryId());
                            secondLevelCategoryVO.setThirdLevelCategoryVOS((BeanUtil.copyList(tempCarsCategories, ThirdLevelCategoryVO.class)));
                            secondLevelCategoryVOS.add(secondLevelCategoryVO);
                        }
                    }
                    //1
                    if (!CollectionUtils.isEmpty(secondLevelCategoryVOS)) {
                        Map<Long, List<SecondLevelCategoryVO>> secondLevelCategoryVOMap = secondLevelCategoryVOS.stream().collect(groupingBy(SecondLevelCategoryVO::getParentId));
                        for (CarsCategory firstCategory : firstLevelCategories) {
                            CarBMallIndexCategoryVO carBMallIndexCategoryVO = new CarBMallIndexCategoryVO();
                            BeanUtil.copyProperties(firstCategory, carBMallIndexCategoryVO);
                            if (secondLevelCategoryVOMap.containsKey(firstCategory.getCategoryId())) {
                                //2
                                List<SecondLevelCategoryVO> tempCarsCategories = secondLevelCategoryVOMap.get(firstCategory.getCategoryId());
                                carBMallIndexCategoryVO.setSecondLevelCategoryVOS(tempCarsCategories);
                                carBMallIndexCategoryVOS.add(carBMallIndexCategoryVO);
                            }
                        }
                    }
                }
            }
            return carBMallIndexCategoryVOS;
        } else {
            return null;
        }
    }

    @Override
    public SearchPageCategoryVO getCategoriesForSearch(Long categoryId) {
        SearchPageCategoryVO searchPageCategoryVO = new SearchPageCategoryVO();
        CarsCategory thirdLevelCarsCategory = carsCategoryMapper.selectByPrimaryKey(categoryId);
        if (thirdLevelCarsCategory != null && thirdLevelCarsCategory.getCategoryLevel() == CarBMallCategoryLevelEnum.LEVEL_THREE.getLevel()) {
            //2
            CarsCategory secondLevelCarsCategory = carsCategoryMapper.selectByPrimaryKey(thirdLevelCarsCategory.getParentId());
            if (secondLevelCarsCategory != null && secondLevelCarsCategory.getCategoryLevel() == CarBMallCategoryLevelEnum.LEVEL_TWO.getLevel()) {
                //3
                List<CarsCategory> thirdLevelCategories = carsCategoryMapper.selectByLevelAndParentIdsAndNumber(Collections.singletonList(secondLevelCarsCategory.getCategoryId()), CarBMallCategoryLevelEnum.LEVEL_THREE.getLevel(), Constants.SEARCH_CATEGORY_NUMBER);
                searchPageCategoryVO.setCurrentCategoryName(thirdLevelCarsCategory.getCategoryName());
                searchPageCategoryVO.setSecondLevelCategoryName(secondLevelCarsCategory.getCategoryName());
                searchPageCategoryVO.setThirdLevelCategoryList(thirdLevelCategories);
                return searchPageCategoryVO;
            }
        }
        return null;
    }

    @Override
    public List<CarsCategory> selectByLevelAndParentIdsAndNumber(List<Long> parentIds, int categoryLevel) {
        return carsCategoryMapper.selectByLevelAndParentIdsAndNumber(parentIds, categoryLevel, 0);//0 means all
    }
}
