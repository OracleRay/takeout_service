package imau.ray.takeout_service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import imau.ray.takeout_service.entity.Orders;

public interface OrderService extends IService<Orders> {

    /**
     * 用户下单
     * @param orders
     */
    public void submit(Orders orders);
}
