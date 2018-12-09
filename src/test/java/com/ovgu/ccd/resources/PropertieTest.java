package com.ovgu.ccd.resources;

import com.ovgu.ccd.applogic.ResourceManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Properties;

public class PropertieTest {

    private ResourceManager rm;

    @Before
    public void setUp() {
        rm = ResourceManager.getInstance();
    }

    @Test
    public void testJChessAppPropertyLoading() {
        Properties jChessApp = rm.getJChessAppPropertie();
        Assert.assertNotNull(jChessApp);
    }

    @Test
    public void testJChessViewPropertyLoading() {
        Properties jChessView = rm.getJChessViewPropertie();
        Assert.assertNotNull(jChessView);
    }

    @Test
    public void testJChessAboutBoxPropertyLoading() {
        Properties jChessAboutBox = rm.getJChessAppPropertie();
        Assert.assertNotNull(jChessAboutBox);
    }


}
