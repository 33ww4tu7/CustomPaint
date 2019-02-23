package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import sample.Figures.*;

@SuppressWarnings("SuspiciousMethodCalls")
public class Controller {
    @FXML
    Canvas canvas;
    private double x1;
    private double y1;

    private AllFigures figures = new AllFigures();

    private BaseFigure figure = new Line();

    public void st(MouseEvent mouseEvent) {
        x1 = mouseEvent.getSceneX();
        y1 = mouseEvent.getSceneY() - 77;
    }

    public void end(MouseEvent mouseEvent) {
        double x2 = mouseEvent.getSceneX();
        double y2 = mouseEvent.getSceneY() - 77;
        figure.Draw(canvas, x1, y1, x2, y2);
    }

    public void clickLine(ActionEvent actionEvent) {
        //   figure = figures.getFiguresList().get(figures.getFiguresList().indexOf(new Line()));
        if (!figures.getFiguresList().contains(Line.class)) {
            figure = new Line();
            figures.getFiguresList().add(figure);
        }
    }

    public void clickRect(ActionEvent actionEvent) {
        // figure = figures.getFiguresList().get(figures.getFiguresList().indexOf(new Rectangle()));
        if (!figures.getFiguresList().contains(Rectangle.class)) {
            figure = new Rectangle();
            figures.getFiguresList().add(figure);
        }
    }

    public void clickOval(ActionEvent actionEvent) {
        // figure = figures.getFiguresList().get(figures.getFiguresList().indexOf(new Rectangle()));
        if (!figures.getFiguresList().contains(Oval.class)) {
            figure = new Oval();
            figures.getFiguresList().add(figure);
        }
    }

    public void clickTriangle(ActionEvent actionEvent) {
        // figure = figures.getFiguresList().get(figures.getFiguresList().indexOf(new Rectangle()));
        if (!figures.getFiguresList().contains(Triangle.class)) {
            figure = new Triangle();
            figures.getFiguresList().add(figure);
        }
    }

    public void clickSquare(ActionEvent actionEvent) {
        // figure = figures.getFiguresList().get(figures.getFiguresList().indexOf(new Rectangle()));
        if (!figures.getFiguresList().contains(Square.class)) {
            figure = new Square();
            figures.getFiguresList().add(figure);
        }
    }
    public void clickRoundRect(ActionEvent actionEvent) {
        // figure = figures.getFiguresList().get(figures.getFiguresList().indexOf(new Rectangle()));
        if (!figures.getFiguresList().contains(RoundRect.class)) {
            figure = new RoundRect();
            figures.getFiguresList().add(figure);
        }
    }
}
