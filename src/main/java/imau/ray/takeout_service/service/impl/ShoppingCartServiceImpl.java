package imau.ray.takeout_service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import imau.ray.takeout_service.entity.ShoppingCart;
import imau.ray.takeout_service.mapper.ShoppingCartMapper;
import imau.ray.takeout_service.service.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {

}
