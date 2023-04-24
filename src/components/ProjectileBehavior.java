package components;

import entity.Entity;
import events.CollisionEvent;
import events.EventBus;
import world.GameWorld;

import java.awt.*;

public class ProjectileBehavior extends Component{
    int multiplier = 1;
    public ProjectileBehavior(Entity entity, EventBus eventBus, String order) {
        super(entity, eventBus);
        eventBus.register(CollisionEvent.class, this::onCollision);
        if (order.equals("left"))
            multiplier = multiplier * -1;

    }

    public void onCollision(CollisionEvent collisionEvent){

        if (collisionEvent.sourceCollider == entity && !collisionEvent.targetCollider.tag.equals("background") && !collisionEvent.targetCollider.tag.equals("projectile")  ){
            GameWorld.entities.remove(collisionEvent.targetCollider);
            GameWorld.entities.remove(collisionEvent.sourceCollider);
        }
    }

    @Override
    public void update() {
        entity.getComponent(Motion.class).accelerateDx(15 * multiplier);
    }
}
