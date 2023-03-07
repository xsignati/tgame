package world;
import components.Camera;
import components.Motion;
import config.ScreenSizes;
import entity.Entity;
import game.*;
import levels.Level;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CurrentFrame {
    List<Entity> entites = GameManager.entities;
    List<Entity> fullEntities = Level.levelEntities;
    public void update(Camera camera) {
        deletePassedByEntities(camera);
        loadEntities(camera);

    }
    private void deletePassedByEntities(Camera camera){
        int currX = camera.currentX;
        entites.removeIf(e -> e.getComponent(Motion.class).getX() < currX - ScreenSizes.EVERY_ENTITY_SIZE);
    }
    private void loadEntities(Camera camera){
        int currX = camera.currentX;
        Map<Boolean, List<Entity>> split = fullEntities.stream().collect(Collectors.partitioningBy(e -> {
            Motion m = e.getComponent(Motion.class);
            return m.getX() >= currX && m.getX() <= currX + ScreenSizes.WIDTH_SIZE;
        }));
        entites.addAll(split.get(Boolean.TRUE));
        fullEntities = split.get(Boolean.FALSE);
    }
}
