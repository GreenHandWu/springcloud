package com.wzm.dao;

import com.wzm.entity.Test;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TestDao {

    Test queryTestById(@Param("id") Long id);

    int addTest(Test test);

}
