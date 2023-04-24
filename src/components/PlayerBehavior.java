package components;

import config.ScreenSizes;
import entity.Entity;
import events.CollisionEvent;
import events.EventBus;
import input.GameController;
import levels.LevelCreator;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PlayerBehavior extends Component {
    PlayerController playerController = new PlayerController();
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

    }
    int delay = 10;
    public void fire(){
        if(delay == 10) {
            LevelCreator.createProjectile(entity.position.x + ScreenSizes.EVERY_ENTITY_SIZE + ScreenSizes.BASIC_UNIT_SIZE,entity.position.y, "right");
            delay = 0;
        }
        else {
            delay++;
        }
    }

    @Override
    public void update() {
        playerController.update();
    }
    private class PlayerController{
        Motion motion = entity.getComponent(Motion.class);
        public void update() {
            if (GameController.isPushed(KeyEvent.VK_W)) {
                motion.up();
            }
            if (GameController.isPushed(KeyEvent.VK_S)) {
                motion.down();
            }
            if (GameController.isPushed(KeyEvent.VK_A)) {
                motion.left();
            }
            if (GameController.isPushed(KeyEvent.VK_D)) {
                motion.right();
            }
            if (GameController.isPushed(KeyEvent.VK_SPACE)) {
                motion.jump();
            }
            if (GameController.isPushed(KeyEvent.VK_F)) {
                fire();
            }
        }
    }
}
