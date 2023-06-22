package org.geepawhill.jltk;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class Main extends Application {
    public static void main(String... args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane pane = new StackPane();
        Label label = new Label("Close me!");
        pane.getChildren().add(label);
        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
    }
}
