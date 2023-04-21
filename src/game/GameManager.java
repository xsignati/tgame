package game;

import components.*;
import entity.Entity;
import input.GameController;
import levels.LevelCreator;
import graphics.Renderer;
import physics.CollisionDetector;
import levels.Level;
import world.GameWorld;


import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class GameManager implements Game {
    private GameWorld gameWorld;

    private Renderer renderer;
    public GameManager(Renderer view) {
        this.renderer = view;
        view.start();
        LevelCreator level = new LevelCreator();
        level.view = view;
        level.create();
        gameWorld = new GameWorld();
        player = level.player;
        currentFrame = new Level(player);
    }

    public void update(){
        updateLogic();
    }


    CollisionDetector collisionDetector = new CollisionDetector();
    Entity player;
    Level currentFrame;
    private void updateLogic(){
        currentFrame.update();
       // GameWorld.entities.stream().flatMap(entity -> entity.getAllComponents().stream()).filter(component -> component.getClass() != Motion.class && component.getClass() != Sprite.class).forEach(Component::update);
        GameWorld.getActiveComponents(GameController.class).forEach(GameController::update);
        GameWorld.getActiveComponents(Camera.class).forEach(Camera::update);
        GameWorld.getActiveComponents(Gravity.class).forEach(Gravity::update);
        GameWorld.getActiveComponents(Collider.class).forEach(Collider::update);
        GameWorld.getActiveComponents(PlayerBehavior.class).forEach(PlayerBehavior::update);
        GameWorld.getActiveComponents(EnemyBehavior.class).forEach(EnemyBehavior::update);
        GameWorld.getActiveComponents(ProjectileBehavior.class).forEach(ProjectileBehavior::update);
        System.out.println(GameWorld.getActiveComponents(ProjectileBehavior.class).stream().collect(Collectors.toList()).size());
        collisionDetector.update();
        GameWorld.getActiveComponents(Motion.class).forEach(Motion::update);
        GameWorld.getActiveComponents(Sprite.class).forEach(Sprite::update);

    }
    public static Camera camera;
    public void render() throws InterruptedException, InvocationTargetException {
        runInSwingThread();
    }

    private void runInSwingThread() throws InterruptedException, InvocationTargetException {
        if (!SwingUtilities.isEventDispatchThread()) {
            SwingUtilities.invokeAndWait(() -> {List<Sprite> shapes = new LinkedList<>(camera.shapesToDraw); renderer.update(shapes);});
        }
    }

}
