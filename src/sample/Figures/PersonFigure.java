package sample.Figures;

import javafx.scene.canvas.Canvas;
import sample.BaseFigure;

import java.util.ArrayList;

public class PersonFigure extends BaseFigure {
    public ArrayList<BaseFigure> list = new ArrayList<>();

    @Override
    public void Draw(Canvas canvas) {

        for (BaseFigure f:
                list) {
            double CofX = (getX2()-getX1())/canvas.getWidth();
            double CofY = (getY2()-getY1())/canvas.getHeight();


            double x =getX1()+f.getX1()*CofX;
            double y= getY1() +f.getY1()*CofY;
            double x1=x+(f.getX2() - f.getX1())*CofX;
            double y1=y+(f.getY2() - f.getY1())*CofY;



            BaseFigure res=f.factory();

            res.setX1(x);
            res.setY1(y);
            res.setX2(x1);
            res.setY2(y1);
            res.Draw(canvas);
        }
    }

    @Override
    public BaseFigure factory() {
        PersonFigure figure = new PersonFigure();
        for (BaseFigure f:
                this.list) {
            BaseFigure buf =f.factory();
            buf.setX1(f.getX1());
            buf.setY1(f.getY1());
            buf.setX2(f.getX2());
            buf.setY2(f.getY2());
            figure.list.add(buf);
        }
        return figure;
    }
}
