package game;

import graphics.Renderer;

import java.lang.reflect.InvocationTargetException;

class Main {
    public static void main(String args[]) {
        Renderer renderer = new Renderer();
        Game game = new GameManager(renderer);
        GameLoop gameLoop = new GameLoop(game);
        try {
            gameLoop.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}