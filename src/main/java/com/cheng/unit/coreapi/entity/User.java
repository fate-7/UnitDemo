package com.cheng.unit.coreapi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chengzhiqi
 * @date 2021/3/17 4:40 下午
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class User {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String username;

    private String password;

    public User create() {
        return User.builder().username("mock").password("mock").build();
    }
}
