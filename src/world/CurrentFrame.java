package world;
import components.Camera;
import components.Motion;
import config.ScreenSizes;
import entity.Entity;
import game.*;
import levels.Level;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CurrentFrame {
    List<Entity> entites = GameWorld.entities;
    List<Entity> fullEntities = Level.levelEntities;
    Entity entity;
    private int currentX = 0;
    public CurrentFrame(Entity player) {
        entity = player;
    }

    public void update(Camera camera) {
        Motion playerMotion = entity.getComponent(Motion.class);
        if(playerMotion.getX() + playerMotion.getWidth() + playerMotion.getDx()  >= currentX + ScreenSizes.WIDTH_SIZE * 0.5){
            currentX = (int)(playerMotion.getX() + playerMotion.getWidth() - ScreenSizes.WIDTH_SIZE * 0.5);
        }

        deletePassedByEntities(camera);
        loadNextEntities(camera);

    }

    private void deletePassedByEntities(Camera camera){
        int currX = camera.currentX;
        entites.removeIf(e -> e.getComponent(Motion.class).getX() < currX - ScreenSizes.EVERY_ENTITY_SIZE);
    }
    private void loadNextEntities(Camera camera){
        int currX = camera.currentX;
        Map<Boolean, List<Entity>> split = fullEntities.stream().collect(Collectors.partitioningBy(e -> {
            Motion m = e.getComponent(Motion.class);
            return m.getX() >= currX && m.getX() <= currX + ScreenSizes.WIDTH_SIZE;
        }));
        entites.addAll(split.get(Boolean.TRUE));
        fullEntities = split.get(Boolean.FALSE);
    }
}
