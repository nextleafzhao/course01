package com.hzht.course01.dao;

import com.hzht.course01.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author 黄昭鸿
 * 基于注解的和XML整合MyBatis
 * @create 2019-08-01 下午8:27
 */
public interface UserMapper {
    /**
     * 按ID查询
     *
     * @param id
     * @return 实体类的属性名与表字段不一致（按驼峰命名规范映射）时，使用 @Results 注解指定映射关系
     * 例如
     * * @Results({
     * *     @Result(property = "username", column = "username"),
     * *     @Result(property = "password", column = "password")
     * *  })
     */
    @Select("select * from test.user where id = #{id}")
    @ResultMap("BaseResultMap")
    User getUser(Long id);

    /**
     * 按ID和名字查询
     *
     * @param id
     * @param username 使用 @Param 注解来指定每一个参数的对应关系
     * @return
     */
    @Select("select * from test.user where id = #{id} and username=#{name}")
    User getUserByIdAndName(@Param("id") Long id, @Param("name") String username);

    /**
     * 查询所有
     *
     * @return
     */
    @Select("select * from test.user")
    List<User> getAll();

    /**
     * 使用xml方式
     * 按名字查询
     *
     * @param username
     * @return
     */
    User getUserByName(String username);

    /**
     * 插入数据
     * @param user
     * @return
     */
    @Insert("insert into user (username, password) values (#{username}, #{password})")
    Integer insertUser(User user);
}
