package com.billingsystem.mainapp;

import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Suite
@SelectPackages({
        "com.system.billingsystem.repositories",
        "com.system.billingsystem.services"
})
class MainAppApplicationTests {

    @Test
    void contextLoads() {
    }

}
