import control.Controller;
import view.View;

class Main {
    public static void main(String args[]) {
        View view = new View();
        Controller controller = new Controller(view);
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