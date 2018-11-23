package com.ovgu.ccd.pieces;

import com.ovgu.ccd.applogic.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PlayerTest {

    private Player  player;
    private String name;
    private Player.Colors color;
    private Player.PlayerTypes playerType;



    @Before
    public void initObjects(){
        this.name = "TestPlayer";
        this.color = Player.Colors.black;
        this.playerType= Player.PlayerTypes.localUser;
        this.player = new Player(this.name, this.color.name());
    }

    @Test
    public void testName(){
        Assert.assertEquals(this.name, this.player.getName());
    }

    @Test
    public void testColor(){
        Assert.assertEquals(this.color, this.player.getColor());
    }

    public void testType(){
        Assert.assertEquals(this.playerType, this.player.getPlayerType());
    }
}
