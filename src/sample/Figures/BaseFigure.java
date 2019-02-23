package sample.Figures;

import javafx.scene.canvas.Canvas;

public abstract class BaseFigure {
    abstract public void Draw(Canvas canvas, double x1, double y1, double x2, double y2);
    
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
