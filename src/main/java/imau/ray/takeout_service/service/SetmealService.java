package imau.ray.takeout_service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import imau.ray.takeout_service.dto.SetmealDto;
import imau.ray.takeout_service.entity.Setmeal;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {
    /**
     * 新增套餐，同时需要保存套餐和菜品的关联关系
     * @param setmealDto
     */
    public void saveWithDish(SetmealDto setmealDto);

    public void removeWithDish(List<Long> ids);
}
