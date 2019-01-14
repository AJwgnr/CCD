package com.ovgu.ccd.gui.chessboardListener;


/**
 * @author CCD DeepBlue team
 * @version 1.0
 * @since
 */
public abstract class GeometricShape extends GeometricPrimitiveDrawer {

    /**
     * constructor
     *
     */
    public GeometricShape() {

    }

    /**
     * computes area of specific geometric shape
     *
     * @return  returns area of specific geometric shape
     */
    public abstract double area();
}
