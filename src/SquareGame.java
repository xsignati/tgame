import control.Controller;
import view.View;

public class SquareGame implements Game {
    private Controller controller;

    public SquareGame(Controller controller) {
        this.controller = controller;
    }

    public void update(){
        controller.updateView();
        controller.update();
        controller.updateLevelFragment();
    }
}
