package imau.ray.takeout_service.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import imau.ray.takeout_service.common.R;
import imau.ray.takeout_service.entity.Employee;
import imau.ray.takeout_service.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/*
* 存疑：怎么查看时post请求，get请求，还是put请求
* 区别：
*   GET请求会向数据库发索取数据的请求，从而来获取信息，该请求就像数据库的select操作一样，只是用来查询一下数据，不会修改、增加数据，不会影响资源的内容，即该请求不会产生副作用。无论进行多少次操作，结果都是一样的。
    PUT请求是向服务器端发送数据的，从而改变信息，该请求就像数据库的update操作一样，用来修改数据的内容，但是不会增加数据的种类等，也就是说无论进行多少次PUT操作，其结果并没有不同。
    POST请求同PUT请求类似，都是向服务器端发送数据的，但是该请求会改变数据的种类等资源，就像数据库的insert操作一样，会创建新的内容。几乎目前所有的提交操作都是用POST请求的。
* */

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 员工登录
     * @param request
     * @param employee
     * @return
     */
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee){    // @RequestBody用来获取json数据
        // 1.将页面提交的密码password加密
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes()); // md5加密

        // 2.根据页面提交的用户名username查询数据库
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();  //条件查询
        queryWrapper.eq(Employee::getUsername,employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);

        // 3. 如果么有查询到该用户则返回登录失败
        if(emp == null){
            return R.error("用户名输入错误！");
        }

        // 4.密码比对
        if(!emp.getPassword().equals(password)){
            return R.error("密码输入错误！");
        }

        // 5.查看员工状态，看是否被封禁
        if(emp.getStatus() == 0){
            return R.error("该账号已被封禁！");
        }

        // 6.登录成功，将员工ID存入Session并返回登录成功结果
        request.getSession().setAttribute("employee", emp.getId());
        return R.success(emp);  // 登录成功
    }

    /**
     * 员工退出功能
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        request.getSession().removeAttribute("employee");  // 清除保存在session中的员工id信息
        return R.success("退出登录成功");
    }

    /**
     * 新增员工
     * @param request
     * @param employee
     * @return
     */
    @PostMapping
    public R<String> save(HttpServletRequest request, @RequestBody Employee employee){
        log.info("新增了员工信息：{}",employee.toString());

        // 设置初始密码123456，但是需要进行md5加密
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));

        // 已经用MyMetaObjectHandler自动填充，无需设置
        /*// 设置员工信息创建和更新时间
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());

        // 获得当前登录用户的ID
        Long empID = (Long) request.getSession().getAttribute("employee");

        // 设置是谁新增的员工
        employee.setCreateUser(empID);
        employee.setUpdateUser(empID);*/

        // 向数据库中插入一条记录
        employeeService.save(employee);

        return R.success("新增员工成功");
    }

    /**
     * 员工信息查询
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name){
        log.info("第{}页,共{}页,姓名:{}",page,pageSize,name);

        // 构造分页构造器
        Page pageInfo = new Page(page,pageSize);

        // 构造条件构造器
        LambdaQueryWrapper<Employee> lambdaQueryWrapper = new LambdaQueryWrapper();

        // 添加过滤条件
        lambdaQueryWrapper.like(StringUtils.isNotEmpty(name),Employee::getName,name);

        // 添加排序条件
        lambdaQueryWrapper.orderByDesc(Employee::getUpdateTime);

        // 执行查询
        employeeService.page(pageInfo, lambdaQueryWrapper);

        return R.success(pageInfo);
    }

    /**
     * 根据id信息修改员工的 “启用” 和 “禁用” 功能
     * @param employee
     * @return
     */
    @PutMapping
    public R<String> updata(HttpServletRequest request, @RequestBody Employee employee){
        /*Long empID = (Long) request.getSession().getAttribute("employee");
        employee.setUpdateTime(LocalDateTime.now());
        employee.setUpdateUser(empID);*/
        employeeService.updateById(employee);
        return R.success("修改成功");
    }

    /**
     * 根据id查询员工信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable Long id){  // @PathVariable 表示{id}在路径中;

        Employee employee = employeeService.getById(id);
        if(employee != null){
            return R.success(employee);
        }
        return R.error("没有查找到对应员工信息");
    }

}
