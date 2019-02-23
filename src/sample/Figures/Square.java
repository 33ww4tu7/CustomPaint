package sample.Figures;

import javafx.scene.canvas.Canvas;

public class Square extends BaseFigure {
    @Override
    public void Draw(Canvas canvas, double x1, double y1, double x2, double y2) {
       double size = ((y2 - y1)>(x2 - x1))? (y2 - y1) : (x2 - x1);
       canvas.getGraphicsContext2D().strokeRect(x1, y1, size,size);
    }
}
