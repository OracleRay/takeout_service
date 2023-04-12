package imau.ray.takeout_service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import imau.ray.takeout_service.dto.DishDto;
import imau.ray.takeout_service.entity.Dish;

public interface DishService extends IService<Dish> {
    // 新增菜品，同时插入菜品对应的口味，需要操作dish、dish_flavor两张表
    public void saveWithFlavor(DishDto dishDto);

    //根据id查询菜品信息和对应的口味信息
    public DishDto getByIdWithFlavor(Long id);

    // 更新菜品信息和口味信息
    public void updateWithFlavor(DishDto dishDto);
}
