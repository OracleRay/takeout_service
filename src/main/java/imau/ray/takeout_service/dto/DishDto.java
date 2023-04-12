package imau.ray.takeout_service.dto;

import imau.ray.takeout_service.entity.Dish;
import imau.ray.takeout_service.entity.DishFlavor;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO������չʾ��������֮������ݴ���
 */
@Data
public class DishDto extends Dish {

    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;
}
