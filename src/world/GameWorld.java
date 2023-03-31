package world;

import entity.Entity;

import java.util.LinkedList;
import java.util.List;

public class GameWorld {
    public static List<Entity> entities = new LinkedList<>();
    public static Entity findEntityWithTag(String tag){
        return entities.stream().filter(e -> tag.equals(e.tag))
                .findAny()
                .orElse(null);
    }
}
