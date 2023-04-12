package imau.ray.takeout_service.filter;

import com.alibaba.fastjson.JSON;
import imau.ray.takeout_service.common.BaseContext;
import imau.ray.takeout_service.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
* 检查用户是否完成登录，并进行校验。存在bug
* 使用web的过滤器方法
* */
@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {
    // 路径匹配器，用来匹配通配符的路径
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 1.获取本次请求的URI
        String requestURI = request.getRequestURI();  // /backend/index.html

        String[] urls = new String[]{  // 定义不需要处理的请求
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
                "/common/**",
                "/user/sendMsg",         // 移动端发送短信
                "/user/login"             // 移动端登录
        };

        // 2. 判断本次请求是否需要处理
        boolean check = check(urls, requestURI);

        // 3. 如果不需要处理，则直接放行
        if (check){
            filterChain.doFilter(request,response);
            return;
        }

        // 4.1. 判断网页端登录状态，如果已经登录，则直接放行
        if(request.getSession().getAttribute("employee") != null){
            //设置登录用户的ID
            Long empId = (Long) request.getSession().getAttribute("employee");
            BaseContext.setCurrentId(empId);

            filterChain.doFilter(request,response);
            return;
        }

        // 4.2. 判断移动端登录状态，如果已经登录，则直接放行
        if(request.getSession().getAttribute("user") != null){
            //设置登录用户的ID
            Long userId = (Long) request.getSession().getAttribute("user");
            BaseContext.setCurrentId(userId);

            filterChain.doFilter(request,response);
            return;
        }

        // 5. 如果未登录则返回未登录的结果，通过输出流的方式向客户端页面响应数据
        log.info("用户未登录!!!");
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;

        /*log.info("拦截到了{}",request.getRequestURI());*/
    }

    /**
     * 路径匹配，检查本次请求是否需要放行
     * @param urls
     * @param requestURI
     * @return
     */
    public boolean check(String[] urls, String requestURI){
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if (match)
                return true;
        }
        return false;
    }
}
