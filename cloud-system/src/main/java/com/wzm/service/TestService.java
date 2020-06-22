package com.wzm.service;

import com.wzm.entity.Test;

public interface TestService {

    Test queryTestById(Long id);

    int addTest(Test test);
}
