package game;

import components.*;
import entity.Entity;
import levels.Level;
import graphics.Renderer;
import physics.CollisionDetector;
import world.CurrentFrame;
import world.GameWorld;


import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class GameManager implements Game {
    private GameWorld gameWorld;

    private Renderer renderer;
    public GameManager(Renderer view) {
        this.renderer = view;
        view.start();
        Level level = new Level();
        level.view = view;
        level.create();
        gameWorld = new GameWorld();
        player = level.player;
        currentFrame = new CurrentFrame(player);
    }

    public void update(){
        updateLogic();
    }


    CollisionDetector collisionDetector = new CollisionDetector();
    Entity player;
    CurrentFrame currentFrame;
    private void updateLogic(){
        currentFrame.update(camera);
        GameWorld.entities.stream().flatMap(entity -> entity.getAllComponents().stream()).filter(component -> component.getClass() != Motion.class && component.getClass() != Sprite.class).forEach(Component::update);
        collisionDetector.update();
        GameWorld.entities.stream().map(e -> e.getComponent(Motion.class)).filter(Objects::nonNull).forEach(Motion::update);
        GameWorld.entities.stream().map(e -> e.getComponent(Sprite.class)).filter(Objects::nonNull).forEach(Sprite::update);
    }
    public static Camera camera;
    public void render() throws InterruptedException, InvocationTargetException {
        if (!SwingUtilities.isEventDispatchThread()) {
            SwingUtilities.invokeAndWait(() -> {List<Sprite> shapes = new LinkedList<>(camera.shapesToDraw); renderer.update(shapes);});
        }
    }
}
