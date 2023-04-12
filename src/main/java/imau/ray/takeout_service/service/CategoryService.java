package imau.ray.takeout_service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import imau.ray.takeout_service.entity.Category;

public interface CategoryService extends IService<Category> {
    public void remove(Long id);
}
