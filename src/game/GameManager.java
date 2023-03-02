package game;

import components.*;
import levels.Level;
import graphics.Renderer;
import entities.Entity;
import physics.CollisionDetector;


import javax.swing.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GameManager implements Game {
    public static List<Entity> entities = new LinkedList<>();
    private Renderer view;
    public GameManager(Renderer view) {
        this.view = view;
        view.start();
        Level level = new Level();
        level.view = view;
        level.create();
    }

    public void update(){
        updateLogic();
        updateView();
    }
    CollisionDetector collisionDetector = new CollisionDetector();
    private void updateLogic(){
        entities.stream().flatMap(entity -> entity.getAllComponents().stream()).filter(component -> component.getClass() != Motion.class && component.getClass() != Sprite.class).forEach(Component::update);
        //player.getComponent(Camera.class).update();
        //entities.stream().map(e -> e.getComponent(Collider.class)).filter(Objects::nonNull).forEach(Collider::update);
        collisionDetector.update();
        entities.stream().map(e -> e.getComponent(Motion.class)).filter(Objects::nonNull).forEach(Motion::update);
        entities.stream().map(e -> e.getComponent(Sprite.class)).filter(Objects::nonNull).forEach(Sprite::update);
    }
    public static Camera camera;
    private void updateView(){
        if (!SwingUtilities.isEventDispatchThread()) {
            List<Sprite> shapes = new LinkedList<>(camera.shapesToDraw);
            SwingUtilities.invokeLater(() -> view.update(shapes));
        }
    }

//    public static List<Collider> getColliders(){
//        return entities.stream().map(e -> e.getComponent(Collider.class)).filter(Objects::nonNull).collect(Collectors.toList());
//    }

}
