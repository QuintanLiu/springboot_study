package com.kunkun.jpa.model.primary;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * 作用描述
 *
 * @author xiaoya.zhuge 19:02 2020-07-08
 */
@Entity
@Data
@Table(name = "user")
public class User {
    @Id
    private Integer id;
    /**
     * 页面的名字
     */
    private String name;

    /**
     * 页面的json数据
     */
    private String password;
}
