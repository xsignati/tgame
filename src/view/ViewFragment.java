package view;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class ViewFragment {
    public static final int SIZE = 200;
    Rectangle2D rectangle2D;
    Color color;

    public ViewFragment(Rectangle2D rectangle2D, Color color) {
        this.rectangle2D = rectangle2D;
        this.color = color;
    }

    public Rectangle2D getRectangle2D() {
        return rectangle2D;
    }

    public Color getColor() {
        return color;
    }

}
