package com.kunkun.jpa.primary;

import com.kunkun.jpa.model.primary.User;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * 作用描述
 *
 * @author xiaoya.zhuge 19:02 2020-07-08
 */
public interface UserDao extends JpaRepository<User, Integer>,
    JpaSpecificationExecutor<User> {

    /**
     * 根据title查数据
     *
     * @param id
     * @return
     */
    @Transactional
    @Query("SELECT dr.name from User dr where dr.id = :id")
    String findDataByTitle(@Param("id") Integer id);
}
