 
package ltd.carb.mall.dao;

import ltd.carb.mall.entity.CarsCategory;
import ltd.carb.mall.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CarsCategoryMapper {
    int deleteByPrimaryKey(Long categoryId);

    int insert(CarsCategory record);

    int insertSelective(CarsCategory record);

    CarsCategory selectByPrimaryKey(Long categoryId);

    CarsCategory selectByLevelAndName(@Param("categoryLevel") Byte categoryLevel, @Param("categoryName") String categoryName);

    int updateByPrimaryKeySelective(CarsCategory record);

    int updateByPrimaryKey(CarsCategory record);

    List<CarsCategory> findCarsCategoryList(PageQueryUtil pageUtil);

    int getTotalCarsCategories(PageQueryUtil pageUtil);

    int deleteBatch(Integer[] ids);

    List<CarsCategory> selectByLevelAndParentIdsAndNumber(@Param("parentIds") List<Long> parentIds, @Param("categoryLevel") int categoryLevel, @Param("number") int number);
}