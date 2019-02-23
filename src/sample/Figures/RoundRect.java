package sample.Figures;

import javafx.scene.canvas.Canvas;

public class RoundRect extends BaseFigure{
    @Override
    public void Draw(Canvas canvas, double x1, double y1, double x2, double y2) {
        canvas.getGraphicsContext2D().strokeRoundRect((x1<x2)?x1:x2, (y1<y2)?y1:y2, (x1<x2)?(x2-x1):(x1-x2), (y1<y2)?(y2-y1):(y1-y2), 50, 50);
    }
}
