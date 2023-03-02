package entities;

import components.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Entity {
    public final String tag;
    public boolean collidable = true;

    public Entity(String tag) {
        this.tag = tag;
    }

    private final Set<Component> components = new HashSet<>();

    public void addComponent(Component component){
        components.add(component);
    }

    public <T> T getComponent(Class<T> type){
        Optional<Component> o =  components.stream().filter(c -> c.getClass() == type).findFirst();
        return o.map(type::cast).orElse(null);
    }

    public Set<Component> getAllComponents(){
        return components;
    }
}
