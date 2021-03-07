import control.Controller;
import control.PlayerSteering;
import model.Player;
import view.View;

class Main {
    public static void main(String args[]) {
        Player player = new Player();
        PlayerSteering playerSteering = new PlayerSteering(player);
        View view = new View(playerSteering);
        Controller controller = new Controller(view, playerSteering);
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