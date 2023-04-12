package imau.ray.takeout_service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import imau.ray.takeout_service.entity.AddressBook;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AddressBookMapper extends BaseMapper<AddressBook> {

}
