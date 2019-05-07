package sample.Figures;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AllFigures implements Serializable {

   private List<BaseFigure> figuresList = new ArrayList<>();

    public List<BaseFigure> getFiguresList() {
        return figuresList;
    }

    public void Set (List<BaseFigure> a){
        figuresList=a;
    }

    @Override
    public String toString() {
        return "AllFigures { " +
                figuresList.toString() +
                "}\n";
    }
}
