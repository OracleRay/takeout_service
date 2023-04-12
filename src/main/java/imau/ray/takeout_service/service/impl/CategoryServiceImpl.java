package imau.ray.takeout_service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import imau.ray.takeout_service.common.CustomException;
import imau.ray.takeout_service.entity.Category;
import imau.ray.takeout_service.entity.Dish;
import imau.ray.takeout_service.entity.Setmeal;
import imau.ray.takeout_service.mapper.CategoryMapper;
import imau.ray.takeout_service.service.CategoryService;
import imau.ray.takeout_service.service.DishService;
import imau.ray.takeout_service.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private DishService dishService;

    @Autowired
    private SetmealService setmealService;

    /**
     * 根据id删除分类，删除之前需要进行判断
     * @param id
     */
    @Override
    public void remove(Long id) {
        // 1. 查询当前分类是否关联了菜品
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();

        // 添加查询条件, 根据id查询
        dishLambdaQueryWrapper.eq(Dish::getCategoryId, id);
        int count_dish = (int) dishService.count(dishLambdaQueryWrapper);

        // 如果已经关联，抛出一个业务异常
        if(count_dish > 0){
            throw new CustomException("当前分类下关联了菜品不能删除！");
        }

        // 2. 查询当前分类是否关联了套餐
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();

        // 添加查询条件，根据id查询
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId,id);
        int count_setmeal = (int) setmealService.count(setmealLambdaQueryWrapper);

        // 已经正常关联
        if(count_setmeal > 0){
            throw new CustomException("当前分类下关联了套餐不能删除！");
        }

        // 正常删除
        super.removeById(id);

    }
}
