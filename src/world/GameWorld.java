package world;

import entity.Entity;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GameWorld {
    public static List<Entity> entities = new LinkedList<>();
    public static Entity findEntityWithTag(String tag){
        return entities.stream().filter(e -> tag.equals(e.tag))
                .findAny()
                .orElse(null);
    }

    public static <T> List<T> getActiveComponents(Class<T> type){
        return GameWorld.entities.stream().filter(e -> e.active).map(e -> e.getComponent(type)).filter(Objects::nonNull).collect(Collectors.toList());
    }

}
