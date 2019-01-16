/*
#    This program is free software: you can redistribute it and/or modify
#    it under the terms of the GNU General Public License as published by
#    the Free Software Foundation, either version 3 of the License, or
#    (at your option) any later version.
#
#    This program is distributed in the hope that it will be useful,
#    but WITHOUT ANY WARRANTY; without even the implied warranty of
#    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#    GNU General Public License for more details.
#
#    You should have received a copy of the GNU General Public License
#    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * Authors:
 * Mateusz Sławomir Lach ( matlak, msl )
 * Damian Marciniak
 */
package com.ovgu.ccd.applogic;

import java.io.Serializable;


/**
 * Represents a Player in the Chess Game
 */
public class Player implements Serializable {
    /**
     * name of the player
     */
    private String name;
    /**
     * color of the player
     */
    private Colors color;
    /**
     * player type
     */
    private PlayerTypes playerType;
    /**
     * whether the player is going down in direction
     */
    private boolean goDown;

    /**
     * Enum for the different possible colors for the chess paly
     */
    public enum Colors {
        WHITE, BLACK, GREY

    }

    /**
     * 'Enum representing the different player options in the game
     */
    public enum PlayerTypes {
        LOCALUSER, NETWORKUSER, COMPUTER
    }


    /**
     * @param name of the player
     * @param color of the player
     */

    public Player(String name, String color) {
        this.name = name;
        this.setColor(Colors.valueOf(color));
        this.setGoDown(false);
    }


    /**
     * @return goDown
     */
    public boolean isGoDown() {
        return goDown;
    }

    /**
     * @param goDown value to set
     */
    public void setGoDown(boolean goDown) {
        this.goDown = goDown;
    }


    /**
     * Sets the players name
     *
     * @param name name of player
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Set the players type
     *
     * @param type type of player - enumerate
     */
    public void setType(PlayerTypes type) {
        this.playerType = type;
    }

    /**
     * sets the color of the player Instance
     *
     * @param color to set
     */
    public void setColor(Colors color) {
        this.color = color;
    }


    /**
     * Get the name of the Player instance
     *
     * @return name of player
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the color of the player instance
     *
     * @return players color
     */
    public Colors getColor() {
        return this.color;
    }

    /**
     * Get the playerType instance of the Player instance
     *
     * @return playerType
     */
    public PlayerTypes getPlayerType() {
        return this.playerType;
    }
}