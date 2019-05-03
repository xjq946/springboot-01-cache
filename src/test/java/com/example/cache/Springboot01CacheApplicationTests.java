package com.example.cache;

import com.example.cache.bean.Employee;
import com.example.cache.mapper.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot01CacheApplicationTests {

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;//操作k-v都是字符串的

    @Autowired
    RedisTemplate redisTemplate;//操作k-v都是对象的

    @Autowired
    RedisTemplate empRedisTemplate;

    //测试字符串
    @Test
    public void test01(){
        stringRedisTemplate.opsForValue().append("msg","hello");
    }

    //测试对象
    @Test
    public void test02(){
        Employee employee=employeeMapper.getEmpById(1);
        redisTemplate.opsForValue().set("emp-01",employee);
    }

    //不使用默认的jdk序列化器，使用json序列化器
    @Test
    public void test03(){
        Employee employee=employeeMapper.getEmpById(1);
        empRedisTemplate.opsForValue().set("emp-01",employee);
    }

    @Test
    public void contextLoads() {
        Employee employee=employeeMapper.getEmpById(1);
        System.out.println(employee);
    }

}
