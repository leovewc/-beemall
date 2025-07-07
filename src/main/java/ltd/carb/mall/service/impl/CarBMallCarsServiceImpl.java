 
package ltd.carb.mall.service.impl;

import ltd.carb.mall.common.CarBMallCategoryLevelEnum;
import ltd.carb.mall.common.CarBMallException;
import ltd.carb.mall.common.ServiceResultEnum;
import ltd.carb.mall.controller.vo.CarBMallSearchCarsVO;
import ltd.carb.mall.dao.CarsCategoryMapper;
import ltd.carb.mall.dao.CarBMallCarsMapper;
import ltd.carb.mall.entity.CarBMallCars;
import ltd.carb.mall.entity.CarsCategory;
import ltd.carb.mall.service.CarBMallCarsService;
import ltd.carb.mall.util.BeanUtil;
import ltd.carb.mall.util.CarBMallUtils;
import ltd.carb.mall.util.PageQueryUtil;
import ltd.carb.mall.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CarBMallCarsServiceImpl implements CarBMallCarsService {

    @Autowired
    private CarBMallCarsMapper carsMapper;
    @Autowired
    private CarsCategoryMapper carsCategoryMapper;

    @Override
    public PageResult getCarBMallCarsPage(PageQueryUtil pageUtil) {
        List<CarBMallCars> carsList = carsMapper.findCarBMallCarsList(pageUtil);
        int total = carsMapper.getTotalCarBMallCars(pageUtil);
        PageResult pageResult = new PageResult(carsList, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public String saveCarBMallCars(CarBMallCars cars) {
        CarsCategory carsCategory = carsCategoryMapper.selectByPrimaryKey(cars.getCarsCategoryId());
        if (carsCategory == null || carsCategory.getCategoryLevel().intValue() != CarBMallCategoryLevelEnum.LEVEL_THREE.getLevel()) {
            return ServiceResultEnum.CARS_CATEGORY_ERROR.getResult();
        }
        if (carsMapper.selectByCategoryIdAndName(cars.getCarsName(), cars.getCarsCategoryId()) != null) {
            return ServiceResultEnum.SAME_CARS_EXIST.getResult();
        }
        cars.setCarsName(CarBMallUtils.cleanString(cars.getCarsName()));
        cars.setCarsIntro(CarBMallUtils.cleanString(cars.getCarsIntro()));
        cars.setTag(CarBMallUtils.cleanString(cars.getTag()));
        if (carsMapper.insertSelective(cars) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public void batchSaveCarBMallCars(List<CarBMallCars> carBMallCarsList) {
        if (!CollectionUtils.isEmpty(carBMallCarsList)) {
            carsMapper.batchInsert(carBMallCarsList);
        }
    }

    @Override
    public String updateCarBMallCars(CarBMallCars cars) {
        CarsCategory carsCategory = carsCategoryMapper.selectByPrimaryKey(cars.getCarsCategoryId());
        if (carsCategory == null || carsCategory.getCategoryLevel().intValue() != CarBMallCategoryLevelEnum.LEVEL_THREE.getLevel()) {
            return ServiceResultEnum.CARS_CATEGORY_ERROR.getResult();
        }
        CarBMallCars temp = carsMapper.selectByPrimaryKey(cars.getCarsId());
        if (temp == null) {
            return ServiceResultEnum.DATA_NOT_EXIST.getResult();
        }
        CarBMallCars temp2 = carsMapper.selectByCategoryIdAndName(cars.getCarsName(), cars.getCarsCategoryId());
        if (temp2 != null && !temp2.getCarsId().equals(cars.getCarsId())) {
            return ServiceResultEnum.SAME_CARS_EXIST.getResult();
        }
        cars.setCarsName(CarBMallUtils.cleanString(cars.getCarsName()));
        cars.setCarsIntro(CarBMallUtils.cleanString(cars.getCarsIntro()));
        cars.setTag(CarBMallUtils.cleanString(cars.getTag()));
        cars.setUpdateTime(new Date());
        if (carsMapper.updateByPrimaryKeySelective(cars) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public CarBMallCars getCarBMallCarsById(Long id) {
        CarBMallCars carBMallCars = carsMapper.selectByPrimaryKey(id);
        if (carBMallCars == null) {
            CarBMallException.fail(ServiceResultEnum.CARS_NOT_EXIST.getResult());
        }
        return carBMallCars;
    }

    @Override
    public Boolean batchUpdateSellStatus(Long[] ids, int sellStatus) {
        return carsMapper.batchUpdateSellStatus(ids, sellStatus) > 0;
    }

    @Override
    public PageResult searchCarBMallCars(PageQueryUtil pageUtil) {
        List<CarBMallCars> carsList = carsMapper.findCarBMallCarsListBySearch(pageUtil);
        int total = carsMapper.getTotalCarBMallCarsBySearch(pageUtil);
        List<CarBMallSearchCarsVO> carBMallSearchCarsVOS = new ArrayList<>();
        if (!CollectionUtils.isEmpty(carsList)) {
            carBMallSearchCarsVOS = BeanUtil.copyList(carsList, CarBMallSearchCarsVO.class);
            for (CarBMallSearchCarsVO carBMallSearchCarsVO : carBMallSearchCarsVOS) {
                String carsName = carBMallSearchCarsVO.getCarsName();
                String carsIntro = carBMallSearchCarsVO.getCarsIntro();
                if (carsName.length() > 28) {
                    carsName = carsName.substring(0, 28) + "...";
                    carBMallSearchCarsVO.setCarsName(carsName);
                }
                if (carsIntro.length() > 30) {
                    carsIntro = carsIntro.substring(0, 30) + "...";
                    carBMallSearchCarsVO.setCarsIntro(carsIntro);
                }
            }
        }
        PageResult pageResult = new PageResult(carBMallSearchCarsVOS, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }
}
