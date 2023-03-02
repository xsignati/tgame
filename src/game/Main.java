package game;

import graphics.Renderer;

class Main {
    public static void main(String args[]) {
        Renderer view = new Renderer();
        Game game = new GameManager(view);
        GameLoop gameLoop = new GameLoop(game);
        try {
            gameLoop.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}