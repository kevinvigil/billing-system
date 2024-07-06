package com.system.billingSystem;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectPackages(value = {"com.system.billingSystem.repository"})
public class BillingSystemApplicationTests {}
