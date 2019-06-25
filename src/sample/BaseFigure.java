package sample;

import com.google.gson.annotations.SerializedName;
import javafx.scene.canvas.Canvas;

public abstract class BaseFigure {

    @SerializedName("type")
    private String typeName;
    public  BaseFigure(){
        typeName=getClass().getName();
    }

    private static final String className = BaseFigure.class.getName();
    private double x1, x2;
    private double y1, y2;

    public String getClassName() {
        return className;
    }

    abstract public void Draw(Canvas canvas);

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

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public void setY1(double y1) {
        this.y1 = y1;
    }

    public void setY2(double y2) {
        this.y2 = y2;
    }

    @Override
    public String toString() {
        return "BaseFigure{" +
                "x1=" + x1 +
                ", x2=" + x2 +
                ", y1=" + y1 +
                ", y2=" + y2 +
                "}\n";
    }

    abstract  public BaseFigure factory();


}
