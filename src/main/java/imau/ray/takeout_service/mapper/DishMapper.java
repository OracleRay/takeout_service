package imau.ray.takeout_service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import imau.ray.takeout_service.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DishMapper extends BaseMapper<Dish> {

}
