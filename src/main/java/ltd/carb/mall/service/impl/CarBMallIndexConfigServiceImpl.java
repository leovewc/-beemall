 
package ltd.carb.mall.service.impl;

import ltd.carb.mall.common.ServiceResultEnum;
import ltd.carb.mall.controller.vo.CarBMallIndexConfigCarsVO;
import ltd.carb.mall.dao.IndexConfigMapper;
import ltd.carb.mall.dao.CarBMallCarsMapper;
import ltd.carb.mall.entity.IndexConfig;
import ltd.carb.mall.entity.CarBMallCars;
import ltd.carb.mall.service.CarBMallIndexConfigService;
import ltd.carb.mall.util.BeanUtil;
import ltd.carb.mall.util.PageQueryUtil;
import ltd.carb.mall.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarBMallIndexConfigServiceImpl implements CarBMallIndexConfigService {

    @Autowired
    private IndexConfigMapper indexConfigMapper;

    @Autowired
    private CarBMallCarsMapper carsMapper;

    @Override
    public PageResult getConfigsPage(PageQueryUtil pageUtil) {
        List<IndexConfig> indexConfigs = indexConfigMapper.findIndexConfigList(pageUtil);
        int total = indexConfigMapper.getTotalIndexConfigs(pageUtil);
        PageResult pageResult = new PageResult(indexConfigs, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public String saveIndexConfig(IndexConfig indexConfig) {
        if (carsMapper.selectByPrimaryKey(indexConfig.getCarsId()) == null) {
            return ServiceResultEnum.CARS_NOT_EXIST.getResult();
        }
        if (indexConfigMapper.selectByTypeAndCarsId(indexConfig.getConfigType(), indexConfig.getCarsId()) != null) {
            return ServiceResultEnum.SAME_INDEX_CONFIG_EXIST.getResult();
        }
        if (indexConfigMapper.insertSelective(indexConfig) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public String updateIndexConfig(IndexConfig indexConfig) {
        if (carsMapper.selectByPrimaryKey(indexConfig.getCarsId()) == null) {
            return ServiceResultEnum.CARS_NOT_EXIST.getResult();
        }
        IndexConfig temp = indexConfigMapper.selectByPrimaryKey(indexConfig.getConfigId());
        if (temp == null) {
            return ServiceResultEnum.DATA_NOT_EXIST.getResult();
        }
        IndexConfig temp2 = indexConfigMapper.selectByTypeAndCarsId(indexConfig.getConfigType(), indexConfig.getCarsId());
        if (temp2 != null && !temp2.getConfigId().equals(indexConfig.getConfigId())) {

            return ServiceResultEnum.SAME_INDEX_CONFIG_EXIST.getResult();
        }
        indexConfig.setUpdateTime(new Date());
        if (indexConfigMapper.updateByPrimaryKeySelective(indexConfig) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public IndexConfig getIndexConfigById(Long id) {
        return null;
    }

    @Override
    public List<CarBMallIndexConfigCarsVO> getConfigCarsForIndex(int configType, int number) {
        List<CarBMallIndexConfigCarsVO> carBMallIndexConfigCarsVOS = new ArrayList<>(number);
        List<IndexConfig> indexConfigs = indexConfigMapper.findIndexConfigsByTypeAndNum(configType, number);
        if (!CollectionUtils.isEmpty(indexConfigs)) {
            //all carsId
            List<Long> carsIds = indexConfigs.stream().map(IndexConfig::getCarsId).collect(Collectors.toList());
            List<CarBMallCars> carBMallCars = carsMapper.selectByPrimaryKeys(carsIds);
            carBMallIndexConfigCarsVOS = BeanUtil.copyList(carBMallCars, CarBMallIndexConfigCarsVO.class);
            for (CarBMallIndexConfigCarsVO carBMallIndexConfigCarsVO : carBMallIndexConfigCarsVOS) {
                String carsName = carBMallIndexConfigCarsVO.getCarsName();
                String carsIntro = carBMallIndexConfigCarsVO.getCarsIntro();
                if (carsName.length() > 30) {
                    carsName = carsName.substring(0, 30) + "...";
                    carBMallIndexConfigCarsVO.setCarsName(carsName);
                }
                if (carsIntro.length() > 22) {
                    carsIntro = carsIntro.substring(0, 22) + "...";
                    carBMallIndexConfigCarsVO.setCarsIntro(carsIntro);
                }
            }
        }
        return carBMallIndexConfigCarsVOS;
    }

    @Override
    public Boolean deleteBatch(Long[] ids) {
        if (ids.length < 1) {
            return false;
        }
        return indexConfigMapper.deleteBatch(ids) > 0;
    }
}
