package App;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML private Label myPI,valuesNo,warning;
    @FXML private ComboBox<String> option;
    @FXML private Button button;
    @FXML private Spinner<Integer> spinner;
    @FXML private Group group;
    @FXML private Pane pane;

    class Dot extends Circle{

        public Dot(double X,double Y){
            super(X,Y,1);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        option.getItems().add("Preciznost");
        option.getItems().add("Broj slucajno generisanih vrijednosti");
        option.setValue("Preciznost");
        warning.setWrapText(true);
        spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,1000000000,1,1));
        spinner.setEditable(true);
        Circle circle=new Circle(0,0,pane.getPrefWidth()/2.0-75);
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.FORESTGREEN);
        group.getChildren().add(circle);
        group.setTranslateY(pane.getPrefHeight()/4+25);
        group.setTranslateX(pane.getPrefWidth()/4-35);

    }

    public void onClickStart(ActionEvent actionEvent) {
        int i = spinner.getValue();
        if (i > 100000000) {
            warning.setText("Maksimalan broj slucajnih vrijednosti je 100'000'000");

        } else if (i > 9 && option.getValue().equals("Preciznost")) {
            warning.setText("Maksimalna preciznos je 10");
        } else {
            if (option.getValue().equals("Preciznost")){
                precision(spinner.getValue());
            }
            else{
              numberOfRandomValues(spinner.getValue());
            }}
    }

    public void numberOfRandomValues(int numberOfIteration) {
        Random rand = new Random();
        double u1, u2;
        int Ncirc = 0;
        double PI = 0;


        for (int i = 0; i < numberOfIteration; ) {
            u1 = rand.nextDouble() % 1;
            u2 = rand.nextDouble() % 1;
            Dot dot = new Dot(u1*100,u2*100);
            double temp = Math.sqrt(Math.pow(u1, 2) + Math.pow(u2, 2));

            if (temp < 1){
                dot.setFill(Color.RED);
                Ncirc++;
            }
            group.getChildren().add(dot);
            PI = 4 * ((double) Ncirc / ++i);

        }
        myPI.setText("My PI = "+ PI);
        valuesNo.setText("ValuesNo = "+ numberOfIteration);

    }

    public void precision(int precision) {
        Random rand = new Random();
        double PI = 0;
        double u1, u2;
        int Ncirc = 0;
        int numberOfIteration = 0;
        do {
            numberOfIteration++;
            u1 = rand.nextDouble() % 1;
            u2 = rand.nextDouble() % 1;
            Dot dot = new Dot(u1*100,u2*100);
            double temp = Math.sqrt(Math.pow(u1, 2) + Math.pow(u2, 2));
            if (temp < 1) {
                dot.setFill(Color.RED);
                Ncirc++;
            }
            group.getChildren().add(dot);
            PI = 4 * ((double) Ncirc / numberOfIteration);
        } while (Math.abs(PI - Math.PI) > (1 / Math.pow(10, precision + 1)));

        myPI.setText("My PI = "+ PI);
        valuesNo.setText("ValuesNo = "+ numberOfIteration);

    }


}
