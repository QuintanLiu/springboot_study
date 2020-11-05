package com.kunkun.springboot.dao;

import com.kunkun.springboot.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Created by nowcoder on 2016/7/2.
 */
//Mapper表示现在是跟数据库的一一匹配的。
@Mapper
public interface UserDAO {
    String TABLE_NAME = "user";
    String INSET_FIELDS = " name, password, salt, head_url ";
    String SELECT_FIELDS = " id, name, password, salt, head_url";


    //#{name}表示User里面的属性name。可以直接读取
    @Insert({"insert into ", TABLE_NAME, "(", INSET_FIELDS,
            ") values (#{name},#{password},#{salt},#{headUrl})"})
    int addUser(User user);

    @Select({"select ",SELECT_FIELDS," from ",TABLE_NAME," where id=#{id}"})
    User selectById(int id);

    @Update({"update ",TABLE_NAME," set password=#{password} where id=#{id}"})
    void updatePassword(User user);




}
