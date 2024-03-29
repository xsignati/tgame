package components;

import config.ScreenSizes;
import entity.Entity;
import events.CollisionEvent;
import events.EventBus;
import levels.LevelCreator;
import world.GameWorld;

import java.awt.*;

public class EnemyBehavior extends Component{
    public int sign = 1;

    public EnemyBehavior(Entity entity, EventBus eventBus) {
        super(entity, eventBus);
        eventBus.register(CollisionEvent.class, this::onCollision);
    }

    public void onCollision(CollisionEvent collisionEvent) {
        if (collisionEvent.sourceCollider == entity && collisionEvent.targetCollider.tag.equals("floor")) {
            if (collisionEvent.collidingXY[0]) {
                entity.getComponent(Motion.class).zeroDx();
                if(entity.position.x > collisionEvent.targetCollider.position.x){
                    sign = -1;
                }
                else{
                    sign = 1;
                }

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
        Entity player = GameWorld.findEntityWithTag("player");
        entity.getComponent(Motion.class).accelerateDx(-5 * sign );

        if(Math.abs(player.position.x - entity.position.x) < ScreenSizes.HALF_WIDTH_SIZE){
            if(player.position.y < entity.position.y){
               jump();
            }

            if(Math.abs(player.position.y - entity.position.y) < ScreenSizes.BASIC_UNIT_SIZE){
                fire();
            }

        }
        updateJump();

    }
    int jumpLeft = 20;
    public void updateJump() {
        if(isJumping) {
            if (jumpLeft > 0) {
                jumpLeft--;
                entity.getComponent(Motion.class).accelerateDy(-25);
            } else {
                jumpLeft = 20;
                isJumping = false;
            }
        }
    }

    boolean isJumping = false;
    public void jump(){
        if(entity.getComponent(Motion.class).previousDy == 0 && isJumping == false )
            isJumping = true;
    }

    private void fire(){
        if(delay == 20) {
            LevelCreator.createProjectile(entity.position.x - ScreenSizes.EVERY_ENTITY_SIZE - ScreenSizes.BASIC_UNIT_SIZE,entity.position.y, "left");
            delay = 0;
        }
        else {
            delay++;
        }
    }
}
