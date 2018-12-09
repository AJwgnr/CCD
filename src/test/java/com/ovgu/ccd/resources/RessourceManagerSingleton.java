package com.ovgu.ccd.resources;

import com.ovgu.ccd.applogic.ResourceManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class RessourceManagerSingleton {


    @Before
    public void setUp(){

    }


    @Test
    public void testRessourceManagerSingletonNotNull(){
        ResourceManager rm1 = ResourceManager.getInstance();
        Assert.assertNotNull(rm1);
    }


    @Test
    public void testRessourceManagerSingleton(){
        ResourceManager rm1 = ResourceManager.getInstance();
        ResourceManager rm2 = ResourceManager.getInstance();
        Assert.assertEquals(rm1,rm2);

    }


}
