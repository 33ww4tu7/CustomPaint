package sample.Figures;

import javafx.scene.canvas.Canvas;
import sample.BaseFigure;

import java.io.Serializable;

public class Line extends BaseFigure implements Serializable {

    private static final String className = Line.class.getName();

    @Override
    public void Draw(Canvas canvas) {

        canvas.getGraphicsContext2D().strokeLine(getX1(), getY1(), getX2(), getY2());
    }
    public String getClassName() {
        return className;
    }



    @Override
    public String toString() {
        return "Line{" +
                "x1=" + getX1() +
                "x2=" + getX2() +
                "y1=" + getY1() +
                "y2=" + getY1() +
                "}\n";
    }

    @Override
    public BaseFigure factory() {
        return new Line();
    }
}
