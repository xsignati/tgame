package components;

import entity.Entity;
import events.EventBus;

public abstract class Component {
    public Entity entity;
    public EventBus eventBus;

    public Component(Entity entity, EventBus eventBus) {
        this.entity = entity;
        this.eventBus = eventBus;
    }

    public abstract void update();
}
