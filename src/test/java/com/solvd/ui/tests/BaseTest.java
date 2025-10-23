package com.solvd.ui.tests;

import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.utils.R;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class BaseTest implements IAbstractTest {

    @BeforeClass
    public void setupDriver() {
        getDriver().manage().window().maximize();
    }
}