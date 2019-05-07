package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Figures.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Logger;

public class Controller {
    @FXML
    Canvas canvas;
    private double x1;
    private double y1;

    private Logger log = Logger.getLogger(Controller.class.getName());

    private String className = Line.class.getName();

    private AllFigures figures = new AllFigures();

    public void st(MouseEvent mouseEvent) {
        x1 = mouseEvent.getSceneX();
        y1 = mouseEvent.getSceneY() - 77;
    }

    public void end(MouseEvent mouseEvent) {
        try {
            double x2 = mouseEvent.getSceneX();
            double y2 = mouseEvent.getSceneY() - 77;
            Class c = Class.forName(className);
            BaseFigure figure = (BaseFigure) c.newInstance();
            figure.setX1(x1);
            figure.setX2(x2);
            figure.setY1(y1);
            figure.setY2(y2);
            figure.Draw(canvas);
            figures.getFiguresList().add(figure);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            showAlert("Error occurred while drawing figures", e.toString());
        }
    }

    public void clickLine(ActionEvent actionEvent) {
        className = Line.class.getName();
    }

    public void clickRect(ActionEvent actionEvent) {
        className = Rectangle.class.getName();
    }

    public void clickOval(ActionEvent actionEvent) {
        className = Oval.class.getName();
    }

    public void clickTriangle(ActionEvent actionEvent) {
        className = Triangle.class.getName();
    }

    public void clickSquare(ActionEvent actionEvent) {
        className = Square.class.getName();
    }

    public void clickRoundRect(ActionEvent actionEvent) {
        className = RoundRect.class.getName();
    }

    public void clickSave(ActionEvent actionEvent) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("save.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeInt(figures.getFiguresList().size());
            for (BaseFigure figure : figures.getFiguresList()) {
                objectOutputStream.writeUTF(figure.getClassName());
                objectOutputStream.writeUTF(Double.toString(figure.getX1()));
                objectOutputStream.writeUTF(Double.toString(figure.getX2()));
                objectOutputStream.writeUTF(Double.toString(figure.getY1()));
                objectOutputStream.writeUTF(Double.toString(figure.getY2()));
                objectOutputStream.writeChar(13);
            }
            objectOutputStream.close();
        } catch (Exception e) {
            log.info(e.toString());
            showAlert("Error occurred while saving figures", e.toString());
        }
    }

    public void clickDownload(ActionEvent actionEvent) {
        try {
            FileInputStream fileInputStream = new FileInputStream("save.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            int counter = objectInputStream.readInt();
            for (int i = 0; i<counter; i++) {
                className = objectInputStream.readUTF();
                Class c = Class.forName(className);
                BaseFigure figure = (BaseFigure) c.newInstance();
                figure.setX1(Double.parseDouble(objectInputStream.readUTF()));
                figure.setX2(Double.parseDouble(objectInputStream.readUTF()));
                figure.setY1(Double.parseDouble(objectInputStream.readUTF()));
                figure.setY2(Double.parseDouble(objectInputStream.readUTF()));
                objectInputStream.readChar();
                figure.Draw(canvas);
                figures.getFiguresList().add(figure);
            }
            objectInputStream.close();
        } catch (Exception e) {
            log.info(e.toString());
            showAlert("Error occurred while download figures", e.toString());
        }
    }

    public void clickClear(ActionEvent actionEvent) {
        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        figures.getFiguresList().clear();
    }
    private void showAlert(String headerMessage,String errorMessage){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error Dialog");
        alert.setHeaderText(headerMessage);
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }
}
