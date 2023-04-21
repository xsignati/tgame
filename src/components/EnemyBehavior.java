package components;

import config.ScreenSizes;
import entity.Entity;
import events.CollisionEvent;
import events.EventBus;
import levels.LevelCreator;

import java.awt.*;

public class EnemyBehavior extends Component{
    public int move = 5;
    public EnemyBehavior(Entity entity, EventBus eventBus) {
        super(entity, eventBus);
        eventBus.register(CollisionEvent.class, this::onCollision);
    }

    public void onCollision(CollisionEvent collisionEvent) {
        if (collisionEvent.sourceCollider == entity && collisionEvent.targetCollider.tag.equals("floor")) {
            if (collisionEvent.collidingXY[0]) {
                entity.getComponent(Motion.class).reverseDx();
                move = move * -1;
            }
            if ((collisionEvent.collidingXY[1])) {
                entity.getComponent(Motion.class).zeroDy();
            }

        }
        if(collisionEvent.sourceCollider.tag.equals("enemy") && collisionEvent.targetCollider.tag.equals("player")) {
            entity.getComponent(Sprite.class).color = Color.ORANGE;

        }
    }
    int delay = 0;
    @Override
    public void update() {
        entity.getComponent(Motion.class).accelerateDx(-move);
        if(delay == 20) {
            LevelCreator.createProjectile(entity.position.x -  ScreenSizes.EVERY_ENTITY_SIZE,entity.position.y, "left");
            delay = 0;
        }
        else {
            delay++;
        }
    }
}
