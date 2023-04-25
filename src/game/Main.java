package game;

import graphics.Renderer;
import input.GameController;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;

class Main {
    public static void main(String args[]) {
        Renderer renderer = new Renderer();
        GameController gameController = new GameController();
        Game game = new GameManager(renderer, gameController);
        GameLoop gameLoop = new GameLoop(game);
        try {
            gameLoop.run();
        } catch (InterruptedException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}