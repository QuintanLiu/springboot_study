package com.kunkun.springboot.mapper;

import com.kunkun.springboot.model.NewsDO;

public interface NewsDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NewsDO record);

    int insertSelective(NewsDO record);

    NewsDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NewsDO record);

    int updateByPrimaryKey(NewsDO record);
}