package com.system.billingsystem;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages({
        "com.system.billingsystem.repositories",
        "com.system.billingsystem.services"
})
public class BillingSystemApplicationTests {}
