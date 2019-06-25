package sample.Figures;

import javafx.scene.canvas.Canvas;
import sample.BaseFigure;

import java.io.Serializable;

public class Oval extends BaseFigure implements Serializable {

    private static final String className = Oval.class.getName();
    private double x1, x2;
    private double y1, y2;

    @Override
    public void Draw(Canvas canvas) {
        double x1 = this.x1;
        double x2 = this.x2;
        double y1 = this.y1;
        double y2 = this.y2;
        canvas.getGraphicsContext2D().strokeOval((x1<x2)?x1:x2, (y1<y2)?y1:y2, (x1<x2)?(x2-x1):(x1-x2), (y1<y2)?(y2-y1):(y1-y2));
    }

    public String getClassName() {
        return className;
    }

    public double getY2() {
        return y2;
    }

    public double getY1() {
        return y1;
    }

    public double getX2() {
        return x2;
    }

    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public void setY1(double y1) {
        this.y1 = y1;
    }

    public void setY2(double y2) {
        this.y2 = y2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    @Override
    public String toString() {
        return "Oval{" +
                "x1=" + x1 +
                "x2=" + x2 +
                "y1=" + y1 +
                "y2=" + y2 +
                "}\n";
    }
}