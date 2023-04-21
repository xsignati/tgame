package components;

import config.ScreenSizes;
import entity.Entity;
import events.CollisionEvent;
import events.EventBus;
import levels.LevelCreator;

import java.awt.*;

public class PlayerBehavior extends Component {
    public PlayerBehavior(Entity entity, EventBus eventBus) {
        super(entity, eventBus);
        eventBus.register(CollisionEvent.class, this::onCollision);
    }


    public void onCollision(CollisionEvent collisionEvent) {
        if (collisionEvent.sourceCollider.tag.equals("player") && collisionEvent.targetCollider.tag.equals("floor")) {
            if (collisionEvent.collidingXY[0]) {
                entity.getComponent(Motion.class).zeroDx();
            }
            if ((collisionEvent.collidingXY[1])) {
                entity.getComponent(Motion.class).zeroDy();
            }

        }
        if (collisionEvent.sourceCollider.tag.equals("player") && collisionEvent.targetCollider.tag.equals("enemy")) {
            entity.getComponent(Sprite.class).color = Color.GREEN;

        }



//        if(other.tag.equals("background")){
//            if(entity.getComponent(Collider.class).getX() + entity.getComponent(Collider.class).getWidth()*0.5 >= other.getComponent(Collider.class).getX()) {
//                Sprite o = other.getComponent(Sprite.class);
//                entity.getComponent(Sprite.class).setColor(new Color(o.getColor().getRed() , o.getColor().getGreen() - 170, o.getColor().getBlue() - 170,170));
//
//            }
//        }
    }
    int delay = 10;
    public void fire(){
        if(delay == 10) {
            LevelCreator.createProjectile(entity.position.x + ScreenSizes.EVERY_ENTITY_SIZE,entity.position.y, "right");
            delay = 0;
        }
        else {
            delay++;
        }
    }

    @Override
    public void update() {
    }
}
