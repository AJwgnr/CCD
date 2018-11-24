package com.ovgu.ccd.gui.chessboardListener;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class ChessboardLabeling extends GeometricPrimitiveDrawer
{
    private Hexagon hexagonForLabeling = null;
    private List<TextLabel> labels = new ArrayList<TextLabel>();
    private String[] labelNames = null;
    private int numOfLabelsPerSide = 8;


    public ChessboardLabeling(Hexagon chessboardHexagon, String[] labels)
    {
        this.labelNames = labels;
        this.hexagonForLabeling = extendHexagonForLabeling(chessboardHexagon);
        this.numOfLabelsPerSide = getNumOfLabelsPerSide();
        assignTextLabels();
    }


    private int getNumOfLabelsPerSide()
    {
        int numOfVertices = this.hexagonForLabeling.getVerticesAsMap().size();
        return (this.labelNames.length + 1) / numOfVertices;
    }


    private Hexagon extendHexagonForLabeling(Hexagon originalHexagon)
    {
        return new Hexagon(
                originalHexagon.getCenter(),
                (int)(originalHexagon.getRadius() * 1.05));
    }


    @Override
    public void draw(Graphics graphics)
    {
        for (TextLabel label : labels)
            label.draw(graphics);
    }


    private void assignTextLabels()
    {
        // get list of positions
        List<Point> points = new ArrayList<Point>();
        for (Line line : this.hexagonForLabeling.getLinesAsList())
        {
            points.addAll(line.getEquallyDistributedPoints(this.numOfLabelsPerSide * 2 + 1));
            points.remove(points.get(points.size() - 1)); // remove last point (otherwise its twice in list)
        }

        // add labels with position and text
        for (int i = 0; i < this.labelNames.length; i++)
            this.labels.add(new TextLabel(this.labelNames[i], points.get(2*i+1)));
    }
}
