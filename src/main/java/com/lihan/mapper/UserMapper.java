package com.lihan.mapper;

import com.lihan.domain.User;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper //标记该类是一个mybatis的mapper接口，可以被spring boot自动扫描到spring上下文中
public interface UserMapper {
    public List<User> queryUserList();
}
