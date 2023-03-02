package components;

import entities.Entity;
import events.EventBus;

public abstract class Behaviour extends Component implements Collidable{
    public Behaviour(Entity entity, EventBus eventBus) {
        super(entity, eventBus);
    }
}
