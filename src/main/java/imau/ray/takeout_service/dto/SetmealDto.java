package imau.ray.takeout_service.dto;

import imau.ray.takeout_service.entity.Setmeal;
import imau.ray.takeout_service.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
