package imau.ray.takeout_service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import imau.ray.takeout_service.entity.OrderDetail;
import imau.ray.takeout_service.mapper.OrderDetailMapper;
import imau.ray.takeout_service.service.OrderDetailService;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

}