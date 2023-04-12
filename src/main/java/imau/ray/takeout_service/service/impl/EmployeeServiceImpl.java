package imau.ray.takeout_service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import imau.ray.takeout_service.entity.Employee;
import imau.ray.takeout_service.mapper.EmployeeMapper;
import imau.ray.takeout_service.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
