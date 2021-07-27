package com.cheng.unit.coreapi.service;

import com.cheng.unit.coreapi.CoreapiApplicationTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MathServiceTest {


    @Test
    @DisplayName("test add")
    public void test() {
        MathService mathService = new MathService();

        final int add = mathService.add(1, 1);

        Assertions.assertEquals(add, 2);
    }

}