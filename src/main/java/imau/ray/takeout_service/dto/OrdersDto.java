package imau.ray.takeout_service.dto;

import imau.ray.takeout_service.entity.OrderDetail;
import imau.ray.takeout_service.entity.Orders;
import lombok.Data;
import java.util.List;

@Data
public class OrdersDto extends Orders {

    private String userName;

    private String phone;

    private String address;

    private String consignee;

    private List<OrderDetail> orderDetails;
	
}
