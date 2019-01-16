package com.ovgu.ccd.gui.chessboardListener;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


/**
 * @author CCD DeepBlue team
 * @version 1.0
 */
public class ChessboardLabeling extends GeometricPrimitiveDrawer
{
    private Hexagon hexagonForLabeling;
    private List<TextLabel> labels = new ArrayList<TextLabel>();
    private String[] labelNames;
    private int numOfLabelsPerSide;
    private final float HEXAGON_STRETCH_FACTOR = 1.05f;


    /**
     * constructor
     *
     * @param   chessboardHexagon   hexagon object of the chessboard
     * @param   labels              array of labels to assign
     */
    public ChessboardLabeling(final Hexagon chessboardHexagon, final String[] labels) {
        this.labelNames = labels;
        this.hexagonForLabeling = extendHexagonForLabeling(chessboardHexagon);
        this.numOfLabelsPerSide = getNumOfLabelsPerSide();
        assignTextLabels();
    }


    /**
     * returns the number of labels per side of the hexagon
     *
     * @return number of labels per side
     */
    private int getNumOfLabelsPerSide() {
        int numOfVertices = this.hexagonForLabeling.getVerticesAsMap().size();
        return (this.labelNames.length + 1) / numOfVertices;
    }


    /**
     * creates a new hexagon with the same center point as the parent one but with increased radius
     *
     * @param   originalHexagon the original parent hexagon
     * @return  the new stretched hexagon
     */
    private Hexagon extendHexagonForLabeling(final Hexagon originalHexagon) {
        return new Hexagon(
                originalHexagon.getCenter(),
                (int) (originalHexagon.getRadius() * HEXAGON_STRETCH_FACTOR));
    }


    /**
     * this method draws the labels
     *
     * @param   graphics    graphics
     */
    @Override
    public void draw(final Graphics graphics) {
        graphics.setColor(Color.BLACK);
        for (TextLabel label : labels)
            label.draw(graphics);
    }


    /**
     * assign the text labels to the chessboard
     * (equally distributed along the sides if the hexagon)
     *
     */
    private void assignTextLabels()
    {
        // get list of positions
        List<Point> points = new ArrayList<Point>();
        for (Line line : this.hexagonForLabeling.getLinesAsList()) {
            points.addAll(line.getEquallyDistributedPoints(this.numOfLabelsPerSide * 2 + 1));
            points.remove(points.get(points.size() - 1)); // remove last point (otherwise its twice in list)
        }

        // add labels with position and text
        for (int i = 0; i < this.labelNames.length; i++)
            this.labels.add(new TextLabel(this.labelNames[i], points.get(2 * i + 1)));
    }
}
