package sample.Figures;

import javafx.scene.canvas.Canvas;
import sample.BaseFigure;

import java.io.Serializable;

public class Rectangle extends BaseFigure implements Serializable {

    private static final String className = Rectangle.class.getName();

    @Override
    public void Draw(Canvas canvas) {
        double x1 = getX1();
        double x2 = getX2();
        double y1 = getY1();
        double y2 = getY2();
        canvas.getGraphicsContext2D().strokeRect((x1<x2)?x1:x2, (y1<y2)?y1:y2, (x1<x2)?(x2-x1):(x1-x2), (y1<y2)?(y2-y1):(y1-y2));
    }
    public String getClassName() {
        return className;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "x1=" + getX1() +
                "x2=" + getX2() +
                "y1=" + getY1() +
                "y2=" + getY2() +
                "}\n";
    }

    @Override
    public BaseFigure factory() {
        return new Rectangle();
    }
}
