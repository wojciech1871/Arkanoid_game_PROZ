import controller.Controller;
import view.View;
import model.Model;

import java.awt.EventQueue;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Model gameModel = new Model();
            View gameView = new View(gameModel);
            Controller gameController = new Controller(gameModel, gameView);
        });
    }
}
