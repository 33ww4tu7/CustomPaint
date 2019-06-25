package sample;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import customClassLoader.MyClassLoader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import sample.Figures.PersonFigure;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {
    @FXML
    MenuButton menuButton;

    @FXML
    Canvas canvas;
    @FXML
    ComboBox<String> comboBox;
    private double x1;
    private double y1;

    private Logger log = Logger.getLogger(Controller.class.getName());

    private String packageName;

    private AllFigures figures = new AllFigures();

    private String modulePath = "customFigures\\";

    private BaseFigure baseFigure;

    private Map<String, Class<?>> classList = new HashMap<>();
    public void st(MouseEvent mouseEvent) {
        x1 = mouseEvent.getSceneX();
        y1 = mouseEvent.getSceneY() - 77;
    }

    public void end(MouseEvent mouseEvent) {
        try {
            double x2 = mouseEvent.getSceneX();
            double y2 = mouseEvent.getSceneY() - 77;
            BaseFigure figure = baseFigure;
            figure.setX1(x1);
            figure.setX2(x2);
            figure.setY1(y1);
            figure.setY2(y2);
            figure.Draw(canvas);
            figures.getFiguresList().add(figure);
            baseFigure=baseFigure.factory();
        } catch (Exception e) {
            showAlert("Error occurred while drawing figures", e.toString());
        }
    }

    @FXML
    public void initialize() {

        MyClassLoader loader = new MyClassLoader(modulePath, "sample.Figures", ClassLoader.getSystemClassLoader());

        File dir = new File(modulePath);
        String[] modules = dir.list();

        if (modules != null) {
            for (String module : modules) {
                try {
                    Pattern pattern = Pattern.compile(".class");
                    Matcher matcher = pattern.matcher(module);
                    if (matcher.find(0)) {
                        String moduleName = module.split(".class")[0];
                        Class clazz = loader.loadClass(moduleName);
                        classList.put(moduleName, clazz);
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        classList.forEach((classKey, customClass) -> comboBox.getItems().add(classKey));
        comboBox.setOnAction(event -> {
            try {
                baseFigure = (BaseFigure) classList.get(comboBox.getValue()).newInstance();
            } catch (IllegalAccessException | InstantiationException e1) {
                e1.printStackTrace();
            }
        });







        File catalog = new File("D:\\untitled1\\src\\figure");
        File[] allfile =catalog.listFiles();
        for (int i = 0; i <allfile.length ; i++) {
            String Name = Integer.toString(i+1);
            MenuItem item1 = new MenuItem("Figure"+Name);
            item1.setOnAction(event -> {
                GsonBuilder builder = new GsonBuilder();
                builder.registerTypeAdapter(BaseFigure.class, new JsonDeserializerWithInheritance<BaseFigure>());
                Gson gson = builder.setPrettyPrinting().create();

                String json= null;
                try {
                    json = ReadInStr("D:\\untitled1\\src\\figure\\"+item1.getText()+".json");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Type itemsArrType = new TypeToken<BaseFigure[]>() {}.getType();
                try {
                    BaseFigure[] arrItemsDes = gson.fromJson(json, itemsArrType);
                    baseFigure = new PersonFigure();
                    for (int j = 0; j < arrItemsDes.length; j++) {
                        ((PersonFigure) baseFigure).list.add(arrItemsDes[j]);
                    }
                } catch (Exception e){

                }
            });
            menuButton.getItems().add(item1);
        }
    }
    public  void WriteInFile(String name,String content) {
        try(FileWriter writer = new FileWriter(name, false))
        {

            writer.write(content);
            writer.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
    public String ReadInStr(String name) throws FileNotFoundException {

        String s = "";
        Scanner in = new Scanner(new File(name));
        while(in.hasNext())
            s += in.nextLine() + "\r\n";
        in.close();
        return s;
    }
    public int CountPersonFigure()
    {
        int result=0;
        File catalog = new File("D:\\untitled1\\src\\figure");
        File[] allfile =catalog.listFiles();
        result=allfile.length;
        return result;
    }

    public void setPersonFigure(ActionEvent actionEvent) {
        baseFigure = new PersonFigure();
        for (BaseFigure figure:
                figures.getFiguresList()) {
            ((PersonFigure) baseFigure).list.add(figure);
        }
        Class a = baseFigure.getClass();
        String Name = Integer.toString(CountPersonFigure()+1);
        MenuItem item1 = new MenuItem("Figure"+Name);
        item1.setOnAction(event -> {
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(BaseFigure.class, new JsonDeserializerWithInheritance<BaseFigure>());
            Gson gson = builder.setPrettyPrinting().create();
            String json= null;
            try {
                json = ReadInStr("D:\\untitled1\\src\\figure\\Figure"+Name+".json");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Type itemsArrType = new TypeToken<BaseFigure[]>() {}.getType();
            try {
                BaseFigure[] arrItemsDes = gson.fromJson(json, itemsArrType);
                baseFigure = new PersonFigure();
                for (int i = 0; i < arrItemsDes.length; i++) {
                    ((PersonFigure) baseFigure).list.add(arrItemsDes[i]);
                }
            } catch (Exception e){

            }
        });
        menuButton.getItems().add(item1);
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(BaseFigure.class, new JsonDeserializerWithInheritance<BaseFigure>());
        Gson gson = builder.setPrettyPrinting().create();
        String derivedClass1Json = gson.toJson(((PersonFigure) baseFigure).list.toArray());
        WriteInFile("D:\\untitled1\\src\\figure\\Figure"+Name+".json",derivedClass1Json);
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
                packageName = objectInputStream.readUTF();
                String[] className = packageName.split("\\.");
                Class c = classList.get(className[className.length - 1]);
                if (c != null) {
                    BaseFigure figure = (BaseFigure) c.newInstance();
                    figure.setX1(Double.parseDouble(objectInputStream.readUTF()));
                    figure.setX2(Double.parseDouble(objectInputStream.readUTF()));
                    figure.setY1(Double.parseDouble(objectInputStream.readUTF()));
                    figure.setY2(Double.parseDouble(objectInputStream.readUTF()));
                    objectInputStream.readChar();
                    figure.Draw(canvas);
                    figures.getFiguresList().add(figure);
                }
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
class JsonDeserializerWithInheritance<Figure> implements JsonDeserializer<Figure> {

    @Override
    public Figure deserialize(
            JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonPrimitive classNamePrimitive = (JsonPrimitive) jsonObject.get("type");
        String className = classNamePrimitive.getAsString();

        Class<?> clazz;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new JsonParseException(e.getMessage());
        }
        return context.deserialize(jsonObject, clazz);
    }
}
