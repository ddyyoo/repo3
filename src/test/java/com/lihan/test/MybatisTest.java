package com.lihan.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lihan.MySpringBootApplication;
import com.lihan.domain.User;
import com.lihan.mapper.UserMapper;
import com.lihan.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MySpringBootApplication.class)
public class MybatisTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test() throws JsonProcessingException {
        //1.从redis中获取数据(数据的形式：json字符串)
        String userListJson = (String) redisTemplate.boundValueOps("user.findAll").get();
        //2.判断redis中是否存在相应数据
        if(userListJson == null) {
            //3.1不存在则从数据库中查询/取数据，同时存入redis数据库中
            List<User> all = userRepository.findAll();
            //将list集合转换成json格式的字符串，使用jackson进行转换
            ObjectMapper objectMapper = new ObjectMapper();
            userListJson = objectMapper.writeValueAsString(all);
            redisTemplate.boundValueOps("user.findAll").set(userListJson);
            System.out.println("=======从数据库中获得user的数据=======");
        }else {
            System.out.println("=======从Redis缓存中获得user的数据=======");
        }
        //4.将数据在控制台上打印
        System.out.println(userListJson);
    }
}
