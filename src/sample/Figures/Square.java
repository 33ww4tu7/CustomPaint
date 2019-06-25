package sample.Figures;

import javafx.scene.canvas.Canvas;
import sample.BaseFigure;

import java.io.Serializable;

public class Square extends BaseFigure implements Serializable {

    private static final String className = Square.class.getName();
    private double x1, x2;
    private double y1, y2;

    @Override
    public void Draw(Canvas canvas) {
        double x1 = getX1();
        double x2 = getX2();
        double y1 = getY1();
        double y2 = getY2();
        double sizeX = (x1 < x2) ? (x2 - x1) : (x1 - x2);
        double sizeY = (y1 < y2) ? (y2 - y1) : (y1 - y2);
        double sizeMin = (sizeX < sizeY) ? sizeX : sizeY;
        canvas.getGraphicsContext2D().strokeRect((x1 < x2) ? x1 : x2, (y1 < y2) ? y1 : y2, sizeMin, sizeMin);
    }

    public String getClassName() {
        return className;
    }

    @Override
    public String toString() {
        return "Square{" +
                "x1=" + x1 +
                "x2=" + x2 +
                "y1=" + y1 +
                "y2=" + y2 +
                "}\n";
    }

    @Override
    public BaseFigure factory() {
        return new Square();
    }
}
