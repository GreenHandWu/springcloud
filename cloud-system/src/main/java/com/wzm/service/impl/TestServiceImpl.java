package com.wzm.service.impl;

import com.wzm.dao.TestDao;
import com.wzm.entity.Test;
import com.wzm.service.TestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TestServiceImpl implements TestService {
    @Resource
    private TestDao testDao;

    @Override
    public Test queryTestById(Long id) {
        return testDao.queryTestById(id);
    }

    @Override
    public int addTest(Test test) {
        return testDao.addTest(test);
    }

}
