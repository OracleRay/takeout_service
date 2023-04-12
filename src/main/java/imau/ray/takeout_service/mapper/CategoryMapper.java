package imau.ray.takeout_service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import imau.ray.takeout_service.entity.Category;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}
