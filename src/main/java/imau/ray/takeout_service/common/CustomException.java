package imau.ray.takeout_service.common;

/**
 * 自定义业务异常分类
 */
public class CustomException extends RuntimeException{
    public CustomException(String message) {
        super(message);
    }
}
