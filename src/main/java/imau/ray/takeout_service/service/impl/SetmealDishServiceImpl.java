package imau.ray.takeout_service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import imau.ray.takeout_service.entity.SetmealDish;
import imau.ray.takeout_service.mapper.SetmealDishMapper;
import imau.ray.takeout_service.service.SetmealDishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class SetmealDishServiceImpl extends ServiceImpl<SetmealDishMapper, SetmealDish> implements SetmealDishService {
}
