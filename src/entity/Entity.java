package entity;

import components.Component;
import components.Position;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Entity {
    public  String tag;
    public boolean active = true;
    public Position position = new Position(0,0);

    public Entity(String tag) {
        this.tag = tag;
    }

    private final Set<Component> components = new HashSet<>();

    public void addComponent(Component component){
        components.add(component);
    }

    public <T> T getComponent(Class<T> type){
        Optional<Component> o =  components.stream().filter(c -> c.getClass() == type || c.getClass().getSuperclass() == type).findFirst();
        return o.map(type::cast).orElse(null);
    }

    public Set<Component> getAllComponents(){
        return components;
    }
}
