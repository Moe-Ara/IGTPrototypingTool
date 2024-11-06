package controller;

import algorithm.TrackingService;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class DistanceMeasurementController implements Controller{
    @FXML
    ScatterChart<Number, Number> s1;
    @FXML
    ScatterChart<Number, Number> s2;
    @FXML
    ScatterChart<Number, Number> s3;
    @FXML
    private TextField point1X;
    @FXML
    private TextField point1Y;
    @FXML
    private TextField point1Z;
    @FXML
    private TextField point2X;
    @FXML
    private TextField point2Y;
    @FXML
    private TextField point2Z;
    @FXML
    private Label distanceLabel;
    private final TrackingService trackingService = TrackingService.getInstance();
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private final BooleanProperty visualizationRunning = new SimpleBooleanProperty(false);
    private final BooleanProperty sourceConnected = new SimpleBooleanProperty(false);


    @FXML
    Button measureDistance;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        registerController();
        measureDistance.disableProperty().bind(visualizationRunning);
    }
    @FXML
    public void measureDistance() {
        try {
            double x1 = Double.parseDouble(point1X.getText());
            double y1 = Double.parseDouble(point1Y.getText());
            double z1 = Double.parseDouble(point1Z.getText());
            double x2 = Double.parseDouble(point2X.getText());
            double y2 = Double.parseDouble(point2Y.getText());
            double z2 = Double.parseDouble(point2Z.getText());

            double distance = calculateDistance(x1, y1, z1, x2, y2, z2);
            DecimalFormat df = new DecimalFormat("#.###");
            distanceLabel.setText("Distance: " + df.format(distance));
        } catch (NumberFormatException e) {
            distanceLabel.setText("Invalid input. Please enter valid numbers.");
        }
    }

    private double calculateDistance(double x1, double y1, double z1, double x2, double y2, double z2) {
        double deltaX = x2 - x1;
        double deltaY = y2 - y1;
        double deltaZ = z2 - z1;
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);
    }

    @Override
    public void registerController() {
        Controller.super.registerController();
    }

    @Override
    public void unregisterController() {
        Controller.super.unregisterController();
    }

    @Override
    public void injectStatusLabel(Label statusLabel) {
        Controller.super.injectStatusLabel(statusLabel);
    }

    @Override
    public void close() {
        unregisterController();
    }


}
