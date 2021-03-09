import control.Controller;
import control.CurrentLevelFragmentController;
import control.PlayerSteering;
import model.CurrentLevelFragment;
import model.Movement2d;
import model.Player;
import view.View;

class Main {
    public static void main(String args[]) {
        Player player = new Player(new Movement2d());
        PlayerSteering playerSteering = new PlayerSteering(player);
        View view = new View(playerSteering);
        CurrentLevelFragment currentLevelFragment = new CurrentLevelFragment();
        CurrentLevelFragmentController currentLevelFragmentController = new CurrentLevelFragmentController(currentLevelFragment);
        currentLevelFragmentController.addEntity(player);
        Controller controller = new Controller(view, playerSteering, currentLevelFragmentController);
        view.start();
        SquareGame game = new SquareGame(controller);
        GameLoop gameLoop = new GameLoop(game);
        try {
            gameLoop.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}