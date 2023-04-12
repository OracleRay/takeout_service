package imau.ray.takeout_service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import imau.ray.takeout_service.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {

}