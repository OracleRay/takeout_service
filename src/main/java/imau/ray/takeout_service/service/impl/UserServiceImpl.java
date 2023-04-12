package imau.ray.takeout_service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import imau.ray.takeout_service.entity.User;
import imau.ray.takeout_service.mapper.UserMapper;
import imau.ray.takeout_service.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService{
}
